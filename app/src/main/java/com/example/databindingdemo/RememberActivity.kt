package com.example.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.databindingdemo.databinding.ActivityRememberBinding

class RememberActivity : AppCompatActivity() {

    private lateinit var bind: ActivityRememberBinding
    private var stated:ObservableBoolean = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_remember)
        bind.stated = stated
        bind.act2 = this

    }

    override fun onDestroy() {
        super.onDestroy()
        bind.unbind()
    }

    fun show()= Toast.makeText(this, if(stated.get())"ok" else "no",Toast.LENGTH_SHORT).show()
}