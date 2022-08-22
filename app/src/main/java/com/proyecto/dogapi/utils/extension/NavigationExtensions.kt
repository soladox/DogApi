package com.proyecto.dogapi.utils.extension

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

fun Fragment.requireNavController() = Navigation.findNavController(requireView())