package com.wojciechmaciejewski.githubapirequester.utils

import android.app.Activity
import android.app.AlertDialog
import com.wojciechmaciejewski.githubapirequester.R

/**
 *
 */
object ErrorHandler {


    fun createErrorDialog(context: Activity, error: Throwable) {
        val alertDialogBuilder = AlertDialog.Builder(context);
        val dialog = alertDialogBuilder.setTitle(context.resources.getString(R.string.error_dialog_tilte)).setMessage(error.message).create()
        dialog.show()
    }
}