package com.example.databindingdemo.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide
import com.example.databindingdemo.App

private const val TAG = "ImageLoader"
@BindingMethods(
    value = [
        BindingMethod(
            type = ImageView::class,
            attribute = "loadUrl",
            method = "loadImage"
        )
    ]
)

object ImageLoader {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun load(iv: ImageView, imgUrl: ObservableField<String>) {
        Log.i(TAG, "load: imgUrl=>${imgUrl.get()}")
        Glide.with(App.mInstance).load(imgUrl.get()).into(iv)
    }
}
