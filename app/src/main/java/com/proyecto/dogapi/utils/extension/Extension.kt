package com.proyecto.dogapi.utils.extension

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity
import android.view.WindowManager
import android.content.Context
import android.widget.Toast
import com.proyecto.dogapi.utils.SafeClickListener

fun ViewGroup.inflate(layoutRes: Int): View{
    return LayoutInflater.from(context).inflate(layoutRes,this,false)
}

fun ImageView.loadUrl(url:String){
    Glide.with(context).load(url).into(this)
}

fun RelativeLayout.showProgress(show: Boolean, activity: FragmentActivity?){
    if(show){
        visibility = View.VISIBLE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }else{
        visibility = View.GONE
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

fun showMessage(message: String, context: Context?){
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

fun View.setSafeOnClickListener(onSafeClick: (View)->Unit){
    val safeClickListener = SafeClickListener{
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}