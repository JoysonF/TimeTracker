package com.here.activetimetracker.utils

import android.content.Context
import android.content.SharedPreferences


class Prefs(context: Context) {

    val PREFS_FILENAME = "AppActiveSessionFile"

    val ACTIVE_SESSION_TIME = "AppActiveSession"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var appActiveSessionDuration: Long
        get() = prefs.getLong(ACTIVE_SESSION_TIME, 0)
        set(value) = prefs.edit().putLong(ACTIVE_SESSION_TIME, value).apply()
}