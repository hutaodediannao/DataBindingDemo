package com.example.databindingdemo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.databindingdemo.BR

class MobileModel : BaseObservable() {

    @get:Bindable
    var mobileName: String = "default"
        set(value) {
            field = value
            notifyPropertyChanged(BR.mobileName)
        }

    @get:Bindable
    var mobileCode: String = "0000-0"
        set(value) {
            field = value
            notifyPropertyChanged(BR.mobileCode)
        }

    fun changeMobileName(){
        this.mobileName = this.mobileName + "-新名称"
    }

    @get:Bindable
    var itemViewType:ItemViewType = ItemViewType.NORMAL_STATE
        set(value) {
            field = value
            notifyPropertyChanged(BR.itemViewType)
        }

}

enum class ItemViewType{
    NORMAL_STATE,
    NO_EDIT_STATE
}