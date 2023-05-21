package com.reem.movies.app.binding.genericadapter.viewholder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickListener

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    protected var onItemClickListener: OnItemClickListener? = null
    var currentPosition: Int = 0
        private set

    protected val context: Context
        get() = itemView.context

    var mBinding: ViewDataBinding? = null

    constructor(itemView: View) : super(itemView) {}

    constructor(binding: ViewDataBinding) : super(binding.root) {
        mBinding = binding
    }

    constructor(itemView: View, onItemClickCallback: OnItemClickCallback?) : super(itemView) {
        if (onItemClickCallback != null) {
            onItemClickListener =
                OnItemClickListener(
                    bindingAdapterPosition,
                    onItemClickCallback
                )
        }
    }

    constructor(
        binding: ViewDataBinding,
        onItemClickCallback: OnItemClickCallback?
    ) : super(binding.root) {
        if (onItemClickCallback != null) {
            onItemClickListener =
                OnItemClickListener(
                    bindingAdapterPosition,
                    onItemClickCallback
                )
        }
        mBinding = binding
    }

    fun bind(listable: Listable, count: Int) {
        mBinding?.setVariable(listable.variableId, listable)
        mBinding?.executePendingBindings()
        draw(listable)
    }

    open fun draw(listable: Listable) {
        if (onItemClickListener != null) {
            onItemClickListener!!.setListableItem(listable)
            onItemClickListener!!.setPosition(bindingAdapterPosition)
        }
    }

    protected fun attachClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(onItemClickListener)
        }
    }

    protected fun find(id: Int): View {
        return itemView.findViewById(id)
    }

    open fun onBind(`object`: Any, position: Int) {
        currentPosition = position
    }

    open fun attachClickChildren(view: View) {
        if (view is ViewGroup) {
            val parent: ViewGroup = view
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                if (child is ViewGroup) {
                    attachClickChildren(child)
                    attachClickListener(child)
                } else {
                    if (child != null) {
                        attachClickListener(child)
                    }
                }
            }
        } else {
            attachClickListener(view)
        }
    }
}

