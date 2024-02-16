package com.nadaalshaibani.donationapp.util

import android.app.AlertDialog
import android.content.Context
import com.nadaalshaibani.donationapp.R

object Config {

    private var dialog: AlertDialog? = null

    fun showDialog(context: Context){
        dialog = MaterialAlertDialogBuilder(context)
            .setView(R.layout.loading_layout)
            .setCancelable(false)
            .create()

        dialog!!.show()
    }
    fun hideDialog(){
        dialog!!.dismiss()
    }


}