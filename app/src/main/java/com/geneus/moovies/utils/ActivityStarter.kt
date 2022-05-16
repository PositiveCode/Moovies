package com.geneus.moovies.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

/*Extension to start an activity*/
inline fun <reified T : Activity> Context.startActivity(intent: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(intent))
}