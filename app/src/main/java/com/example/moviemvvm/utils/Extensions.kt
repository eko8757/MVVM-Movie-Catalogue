package com.example.moviemvvm.utils

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun String.year() : String {
    return this.substring(0,4)
}