package com.metehanbolat.fakestorexml.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T : Any>(
    private val itemLayoutRes: Int = -1,
    diffCallback: DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }
    }
) : ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    var callback: OnItemClickListener<T>? = null

    abstract fun bind(itemView: View, item: T, position: Int, viewHolder: BaseViewHolderImpl)

    inner class BaseViewHolderImpl(itemView: View) : BaseViewHolder<T>(itemView) {
        override fun bind(item: T, position: Int) {
            this@BaseAdapter.bind(itemView, item, position, this)
            callback?.let { notNullCallback ->
                itemView.setOnClickListener {
                    notNullCallback(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BaseViewHolderImpl(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemViewType(position: Int) = itemLayoutRes

    open fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        callback = listener
    }

    fun clearList() {
        submitList(arrayListOf())
    }
}

typealias OnItemClickListener<T> = (data: T) -> Unit