package com.example.databindingdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingdemo.BR
import com.example.databindingdemo.R
import com.example.databindingdemo.databinding.ItemLayBinding
import com.example.databindingdemo.databinding.ItemNoEditLayBinding
import com.example.databindingdemo.model.ItemViewType
import com.example.databindingdemo.model.MobileModel

abstract class BaseAdapter<T>(var context: Context, var list: List<T>) :
    RecyclerView.Adapter<BaseHolder>() {

    override fun getItemCount(): Int = this.list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder =
        BaseHolder.getBaseHolderInstance(context, parent, getLayoutId(viewType))

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind.setVariable(BR.mb, list[position])
        holder.bind.executePendingBindings()
//        bindHolder(holder.bind, list[position])
    }

    abstract fun getLayoutId(viewType: Int): Int

    abstract fun bindHolder(bind:ViewDataBinding, t:T)
}

class BaseHolder(dataBind: ViewDataBinding) : RecyclerView.ViewHolder(dataBind.root) {
    var bind: ViewDataBinding

    init {
        bind = dataBind
    }

    companion object {
        fun getBaseHolderInstance(context: Context, parent: ViewGroup, layoutId: Int): BaseHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
            val dataBind: ViewDataBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
            return BaseHolder(dataBind)
        }
    }
}

class TextAdapter(context: Context, list: List<MobileModel>) :
    BaseAdapter<MobileModel>(context, list) {
    override fun getLayoutId(viewType: Int): Int =
        when (viewType) {
            ItemViewType.NORMAL_STATE.ordinal -> R.layout.item_lay
            ItemViewType.NO_EDIT_STATE.ordinal -> R.layout.item_no_edit_lay
            else -> R.layout.item_lay
        }

    override fun bindHolder(bind: ViewDataBinding, t: MobileModel) {
        if (bind is ItemLayBinding) {
            bind.mb = t
        }else if (bind is ItemNoEditLayBinding) {
            bind.mb = t
        }
    }

    override fun getItemViewType(position: Int): Int = list[position].itemViewType.ordinal
}