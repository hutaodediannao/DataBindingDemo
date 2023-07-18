package com.example.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.example.databindingdemo.databinding.ActivityBindAbleBinding
import com.example.databindingdemo.util.ImageLoader

private const val TAG = "BindAbleActivity"

class BindAbleActivity : AppCompatActivity() {

    private lateinit var bind: ActivityBindAbleBinding
    private val imgIcon: ObservableField<String> by lazy { ObservableField("https://tse4-mm.cn.bing.net/th/id/OIP-C.8PtaBcaLZP4pe6rYlyCSGgHaE0?pid=ImgDet&rs=1") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(
            this,
            R.layout.activity_bind_able
        )
        bind.imgUrl = imgIcon
        bind.act = this
    }

    override fun onDestroy() {
        super.onDestroy()
        bind.unbind()
    }

    fun updateImageView() {
        Log.i(TAG, "updateImageView: 开始加载图片: ${imgIcon.get()}")
        imgIcon.set("https://img2.baidu.com/it/u=3438011236,1721602599&fm=253&fmt=auto&app=138&f=JPEG?w=658&h=500")
    }
}