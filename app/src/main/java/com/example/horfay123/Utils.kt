package com.example.horfay123

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}

fun View.snack(message: String, duration: Int){
    Snackbar.make(this,message,duration).show()

}
fun ProgressBar.show(){
    visibility= View.VISIBLE
}
fun ProgressBar.hide(){
    visibility= View.GONE
}