package com.danipl.slbridge

import android.app.Activity
import android.content.Intent
import android.nfc.NfcAdapter.getDefaultAdapter
import android.provider.Settings
import timber.log.Timber

class MainActivity : Activity() {

    override fun onResume() {
        super.onResume()
        if (getDefaultAdapter(applicationContext).isEnabled) {
            val uriIntent = Intent(Settings.ACTION_NFC_SETTINGS)
            uriIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(uriIntent)
        } else {
            try{
                val uriIntent = Intent(Intent.ACTION_VIEW)
                uriIntent.data = android.net.Uri.parse("sl://")
                uriIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                applicationContext.startActivity(uriIntent)
            }
            catch (e: Exception) {
                Timber.d("SL app could not be opened because: $e")
            }
            finishAndRemoveTask()
        }
    }
    // hello
}

