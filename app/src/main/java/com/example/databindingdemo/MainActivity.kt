package com.example.databindingdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.databinding.PropertyChangeRegistry
import com.example.databindingdemo.databinding.MainLayoutBinding
import com.example.databindingdemo.model.StatusModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainDataBind: MainLayoutBinding
    private val isSuccessObservableBoolean = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDataBind = DataBindingUtil.setContentView(this, R.layout.main_layout)
        mainDataBind.desc = "NOKIA-X6"

        mainDataBind.isSuccess = isSuccessObservableBoolean

        val statusModel = StatusModel().apply {
            desc = "loading..."
            status = 1
        }
        mainDataBind.statusModel = statusModel

        mainDataBind.lifecycleOwner = this

        mainDataBind.act = this
    }

    fun nextPage() {
        startActivity(Intent(this, NextActivity::class.java))
    }

    fun toBindAblePage(){
        startActivity(Intent(this, BindAbleActivity::class.java))
    }

}
