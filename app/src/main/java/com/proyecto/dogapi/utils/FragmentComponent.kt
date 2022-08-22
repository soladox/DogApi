package com.proyecto.dogapi.utils

import androidx.fragment.app.Fragment

interface FragmentComponent<T: Fragment> {
    fun inject(target: T)
}