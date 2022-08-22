package com.proyecto.dogapi.utils

import android.app.Activity

interface ActivityComponent<T: Activity> {
    fun inject(target: T)
}