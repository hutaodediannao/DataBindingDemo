package com.example.databindingdemo

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object{
        @get:Synchronized()
        lateinit var mInstance:App
    }

}