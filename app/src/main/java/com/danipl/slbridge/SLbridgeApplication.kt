package com.danipl.slbridge

import android.app.Application
import timber.log.Timber

class SLbridgeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree());
    }
}