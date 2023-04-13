package com.example.mypractice

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mypractice.databinding.ActivityMainBinding
import com.example.mypractice.databinding.CustomLayoutBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    val TAG : String="DIALOGACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.alertBox.setOnClickListener(this)
        binding.customDialog.setOnClickListener(this)
        binding.progressDialogue.setOnClickListener(this)
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
               val layoutCustomBinding=CustomLayoutBinding.inflate(layoutInflater)
                val dialog=Dialog(this)
                dialog.setContentView(layoutCustomBinding.root)
                dialog.setCancelable(false)
                val windowManager=WindowManager.LayoutParams()
                windowManager.width=LayoutParams.MATCH_PARENT
                windowManager.height=LayoutParams.WRAP_CONTENT
                dialog.window?.attributes=windowManager
                dialog.show()

                layoutCustomBinding.btnSubmit.setOnClickListener {
                    Toast.makeText(this,"${layoutCustomBinding.tilFname.editText?.text.toString()}  " +
                            "${layoutCustomBinding.tilLname.editText?.text.toString()}",Toast.LENGTH_LONG).show()
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
        }
    }
}



