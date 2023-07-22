package com.example.databindingdemo

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "UserViewModel"
class UserViewModel(private val user: User, private val lifecycleOwner: LifecycleOwner) :
    ViewModel() {

    val dataLv by lazy { MutableLiveData(user) }

    fun update(age: Int) {
        this.user.age = age
        this.dataLv.postValue(this.user)
    }

    fun setChecked(isSelected: Boolean){
        this.user.isSelected = isSelected
        dataLv.postValue(user)
    }

    init {
        //activity生命周期结束的时候,activity不再监听数据变化
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                dataLv.removeObservers(lifecycleOwner)
                Log.i(TAG, "onDestroy: 页面结束,开始调用dataLv.removeObservers(lifecycleOwner)")
            }
        })
    }
}

data class User(var name: String, var age: Int, var addr: String, var isSelected:Boolean=false) {
    override fun toString(): String {
        return "User(name='$name', age=$age, addr='$addr, isSelected=$isSelected')"
    }
}
