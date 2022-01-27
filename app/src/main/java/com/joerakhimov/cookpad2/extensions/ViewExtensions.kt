package com.joerakhimov.cookpad2.extensions

import android.view.View

fun View.setVisible(){
    visibility = View.VISIBLE
}

fun View.setInvisible(){
    visibility = View.INVISIBLE
}

fun View.setGone(){
    visibility = View.GONE
}

fun View.showOrGone(visible: Boolean){
    if(visible) setVisible()
    else setGone()
}