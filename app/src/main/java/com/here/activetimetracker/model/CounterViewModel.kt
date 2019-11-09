package com.here.activetimetracker.model

import android.app.Application
import android.os.SystemClock
import androidx.lifecycle.*
import com.here.activetimetracker.utils.DateTimeUtil
import com.here.activetimetracker.utils.Prefs

class CounterViewModel(context: Application) :  AndroidViewModel(context), LifecycleEventObserver {

    fun <T : Any?> MutableLiveData(defaultValue: T) = MutableLiveData<T>().apply { setValue(defaultValue) }

    val counterValue: MutableLiveData<Long> = MutableLiveData(0L)

    private val prefs = Prefs(context)

    private var currentTimeInMs = 0L

    var chronometerFormat: MutableLiveData<String> = MutableLiveData()


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {

            Lifecycle.Event.ON_RESUME -> {
                counterValue.value = SystemClock.elapsedRealtime() - (prefs?.appActiveSessionDuration)
            }

            Lifecycle.Event.ON_PAUSE -> {
                prefs.appActiveSessionDuration = currentTimeInMs
            }
        }
    }


    fun onTick(base: Long) {

        currentTimeInMs = SystemClock.elapsedRealtime() - base

        when {
            DateTimeUtil.isTimeInSeconds(currentTimeInMs) -> {
                chronometerFormat.value =
                    DateTimeUtil.setTimeFormatInSeconds(currentTimeInMs)

            }

            DateTimeUtil.isTimeInMins(currentTimeInMs) -> {
                chronometerFormat.value =
                    DateTimeUtil.setTimeFormatInMins(currentTimeInMs)
            }

            DateTimeUtil.isTimeInHours(currentTimeInMs) -> {
                chronometerFormat.value = DateTimeUtil.setTimeFormatInHours(
                    currentTimeInMs
                )
            }

            DateTimeUtil.isTimeInDays(currentTimeInMs) -> {
                chronometerFormat.value = DateTimeUtil.setTimeFormatInDays(
                    currentTimeInMs
                )
            }
        }
    }

}