package com.proyecto.dogapi.utils

interface IProgressView {

    fun showProgress(show: Boolean)

    fun showMessage(message: String)
}