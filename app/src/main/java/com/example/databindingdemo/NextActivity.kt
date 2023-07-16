package com.example.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingdemo.adapter.TextAdapter
import com.example.databindingdemo.databinding.ActivityNextBinding
import com.example.databindingdemo.model.MobileModel
import com.example.databindingdemo.model.ItemViewType

class NextActivity : AppCompatActivity() {

    private lateinit var mAdapter:TextAdapter
    private val list:List<MobileModel> = ArrayList<MobileModel>().apply {
        for (i in 0..25){
            add(MobileModel().apply {
                this.mobileName = "nokia-x$i"
                this.mobileCode = "0000-$i"
                this.itemViewType = if (i%2==0) ItemViewType.NORMAL_STATE else ItemViewType.NO_EDIT_STATE
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBind = DataBindingUtil.setContentView<ActivityNextBinding>(this, R.layout.activity_next)
        mAdapter = TextAdapter(this, list)
        dataBind.rv.apply {
            layoutManager = LinearLayoutManager(this@NextActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }
}