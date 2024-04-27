package com.danipl.slbridge

import android.app.Application
import com.google.firebase.BuildConfig
import timber.log.Timber

class SLbridgeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree());
        } else {
            Timber.plant(ReleaseTree())
        }

    }
}