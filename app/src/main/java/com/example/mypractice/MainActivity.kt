package com.example.mypractice

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.CalendarView
import android.widget.NumberPicker
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mypractice.databinding.ActivityMainBinding
import com.example.mypractice.databinding.CustomLayoutBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, NumberPicker.OnValueChangeListener,
    RatingBar.OnRatingBarChangeListener {
    private lateinit var binding: ActivityMainBinding
    val TAG : String="DIALOGACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.alertBox.setOnClickListener(this)
        binding.customDialog.setOnClickListener(this)
        binding.progressDialogue.setOnClickListener(this)
        binding.snackbar.setOnClickListener(this)
        binding.customSnakbar.setOnClickListener(this)
        binding.datePicker.setOnClickListener(this)
        binding.timePicker.setOnClickListener(this)

        binding.numberPicker.minValue=0
        binding.numberPicker.maxValue=100
        binding.numberPicker.setOnValueChangedListener(this)
        binding.rating.setOnRatingBarChangeListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.alert_box -> {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle(resources.getString(R.string.app_name))
                alertDialog.setMessage("Do you want to exit?")
                alertDialog.setCancelable(false)
                alertDialog.setPositiveButton("ok", object : DialogInterface.OnClickListener {
                    override fun onClick(di: DialogInterface?, p1: Int) {
                        finish()

                    }
                })
                alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }

                })
                alertDialog.show()

                //           val alerts:AlertDialog=alertDialog.create()
                //        alertDialog.show()
                //      alerts.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.black))
                //    alerts.getButton(DialogInterface.BUTTON_NEGATIVE).isAllCaps=false
            }

            R.id.custom_Dialog -> {
                val layoutCustomBinding = CustomLayoutBinding.inflate(layoutInflater)
                val dialog = Dialog(this)
                dialog.setContentView(layoutCustomBinding.root)
                dialog.setCancelable(false)
                val windowManager = WindowManager.LayoutParams()
                windowManager.width = LayoutParams.MATCH_PARENT
                windowManager.height = LayoutParams.WRAP_CONTENT
                dialog.window?.attributes = windowManager
                dialog.show()

                layoutCustomBinding.btnSubmit.setOnClickListener {
                    Toast.makeText(
                        this,
                        "${layoutCustomBinding.tilFname.editText?.text.toString()}  " +
                                "${layoutCustomBinding.tilLname.editText?.text.toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                    dialog.dismiss()
                }


            }
            R.id.progress_dialogue -> {
                ProgressDialog(this,).apply {
                    this.setCancelable(false)
                    this.setTitle(resources.getString(R.string.app_name))
                    this.setMessage("Please wait")
                    this.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                }.show()

            }
            R.id.snackbar -> {
                Snackbar.make(binding.root, "Hello Snackbar", Snackbar.LENGTH_SHORT)
                    .setAction("Retry", object : View.OnClickListener {
                        override fun onClick(p0: View?) {
                            Toast.makeText(
                                this@MainActivity,
                                "Action click on snackbar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })

                    .show()


            }
            R.id.custom_snakbar -> {
                val snackbar = Snackbar.make(view, "Message", Snackbar.LENGTH_SHORT)
                snackbar.setAction("Change Color") {
                    binding.customSnakbar.setBackgroundColor(Color.RED)
                }
                snackbar.show()
            }
            R.id.date_picker -> {
                //  MaterialDatePicker.Builder.datePicker().build().show(supportFragmentManager,"DATE PICKER")
                val datePickerBuilder = MaterialDatePicker.Builder.datePicker()
                datePickerBuilder.setTitleText("Select your DOB")
                val datePicker =datePickerBuilder.build()

                datePicker.show(supportFragmentManager,"DATE")
                datePicker.addOnPositiveButtonClickListener {
             //       val calendar=Calendar.getInstance()
               //     calendar.time= Date(it)
                //    val DAY=calendar.get(Calendar.DAY_OF_MONTH) +2
                  //  val MONTH=calendar.get(Calendar.MONTH)
                  //  val YEAR =calendar.get(Calendar.YEAR)

                   // Toast.makeText(this@MainActivity,"$DAY/ $MONTH/ $YEAR",Toast.LENGTH_SHORT).show()
                    val simpleDateFormat =SimpleDateFormat("dd/mm/yyyy")
                    val selectedDate=simpleDateFormat.format(Date(it))
                    Toast.makeText(this@MainActivity ,"You have selected: $selectedDate",Toast.LENGTH_LONG).show()
                }
                }
            R.id.time_picker -> {
                val timeBuilder=MaterialTimePicker.Builder()
                timeBuilder.setHour(2)
                timeBuilder.setMinute(30)
                timeBuilder.setTimeFormat(TimeFormat.CLOCK_24H)
                val timePicker=timeBuilder.build()
                timePicker.addOnPositiveButtonClickListener {
                   Toast.makeText( this@MainActivity,"${timePicker.hour} :${timePicker.minute}",Toast.LENGTH_SHORT).show()
                }
                timePicker.show(supportFragmentManager,"TIME PICKER")
            }
            }
        }

    override fun onValueChange(numPicker: NumberPicker?, prevValue: Int, nextValue: Int) {
        Toast.makeText(this@MainActivity, "${numPicker?.value}", Toast.LENGTH_SHORT).show()
    }

    override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
        Log.d(TAG, "onRatingChanged: "+ratingBar?.rating)

    }
}






