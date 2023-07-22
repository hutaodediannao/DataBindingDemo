package com.example.databindingdemo.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide
import com.example.databindingdemo.App

private const val TAG = "ImageLoader"


object ImageLoader {
    @JvmStatic
    @BindingAdapter(value = ["loadImage"], requireAll = true)
    fun load(iv: ImageView, imgUrl: ObservableField<String>) {
        Log.i(TAG, "load: imgUrl=>${imgUrl.get()}")
        Glide.with(App.mInstance).load(imgUrl.get()).into(iv)
    }
}

@BindingMethods(
    value = [
        BindingMethod(type = DescTextView::class, attribute = "tt", method = "setData")
    ]
)
class DescTextView(context: Context, attributeSet: AttributeSet) :androidx.appcompat.widget.AppCompatTextView(context, attributeSet) {
    var desc: String = ""
        set(value) {
            field = value
            text = value
            Log.i(TAG, "setDesc 开始执行...: ")
        }

    var content:ObservableField<String> = ObservableField("")

    fun setData(content:ObservableField<String>){
        this.content = content
        text = this.content.get()
    }
}