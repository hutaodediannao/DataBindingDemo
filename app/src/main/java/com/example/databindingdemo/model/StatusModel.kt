package com.example.databindingdemo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingMethod
import com.example.databindingdemo.BR
import com.example.databindingdemo.R

class StatusModel : BaseObservable(){

    @get:Bindable
    var status:Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }

    @get:Bindable
    var desc:String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.desc)
        }

    init {
        this.status = 0
        this.desc = "加载中..."

    }

    fun getLayout():Int{
        return if (status==1) R.layout.success_layout else R.layout.failed_layout
    }

}