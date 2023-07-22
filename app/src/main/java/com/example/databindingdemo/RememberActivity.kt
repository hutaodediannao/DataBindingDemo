package com.example.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databindingdemo.databinding.ActivityRememberBinding

class RememberActivity : AppCompatActivity() {

    private lateinit var bind: ActivityRememberBinding
    private var stated: ObservableBoolean = ObservableBoolean(false)

    private val user by lazy { User("胡涛", 33, "深圳") }
    private val userVm by lazy { UserViewModel(user, this@RememberActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_remember)
        bind.stated = stated
        bind.act2 = this

        userVm.dataLv.observe(this, observe)

        bind.userVm = userVm
        bind.lifecycleOwner = this
    }

    private val observe by lazy {
        Observer<User> {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bind.unbind()
    }

    fun show() = Toast.makeText(this, if (stated.get()) "ok" else "no", Toast.LENGTH_SHORT).show()

    fun updateUser() {
        val age = user.age + 1
        userVm.update(age)
    }
}