package com.proyecto.dogapi.utils

import android.annotation.SuppressLint
import android.content.Context
//import android.support.multidex.MultiDex
//import android.support.multidex.MultiDexApplication
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object{
        @SuppressLint("StaticFieldLeak") //???
        var instance: Context? = null //quita error en instance de onCreate
        private set
    }
}