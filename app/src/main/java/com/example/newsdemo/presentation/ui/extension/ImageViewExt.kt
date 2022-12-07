package com.example.newsdemo.presentation.ui.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */


fun ImageView.load(url:String){
   Glide.with(context).load(url).into(this)
}