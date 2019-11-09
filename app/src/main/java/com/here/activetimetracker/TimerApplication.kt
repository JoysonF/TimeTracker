package com.here.activetimetracker

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.here.activetimetracker.utils.Prefs

class TimerApplication : Application(), LifecycleObserver {

    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }


}