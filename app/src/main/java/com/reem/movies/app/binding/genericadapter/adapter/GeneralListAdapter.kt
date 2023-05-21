package com.reem.movies.app.binding.genericadapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.binding.genericadapter.viewholder.BaseViewHolder

class GeneralListAdapter(
    context: Context,
    onItemClickCallback: OnItemClickCallback? = null,
    private val useDataBinding: Boolean = true,
    dataDiff: DiffUtil.ItemCallback<Listable>? = null,
) : RecyclerArrayAdapter<Listable, BaseViewHolder>(
    context,
    onItemClickCallback,
    dataDiff ?: DEFAULT_DIFF
) {

    var holderClass: HolderClass? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        when {
            useDataBinding -> {
//                holderClass = getItem(viewType)?.listItemType!!
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding: ViewDataBinding =
                    DataBindingUtil.inflate(
                        layoutInflater,
                        holderClass!!.layoutResId,
                        viewGroup,
                        false
                    )

                return if (onItemClickCallback != null) {
                    val constructor = holderClass!!.viewHolderClass.getConstructor(
                        ViewDataBinding::class.java,
                        OnItemClickCallback::class.java
                    )
                    constructor.newInstance(binding, onItemClickCallback) as BaseViewHolder
                } else {
                    val constructor = holderClass!!.viewHolderClass.getConstructor(
                        ViewDataBinding::class.java
                    )
                    constructor.newInstance(binding) as BaseViewHolder
                }

            }
            else -> {
//                val holderClass = getItem(viewType)?.listItemType!!
                val view = mInflater.inflate(holderClass?.layoutResId!!, viewGroup, false)

                return if (onItemClickCallback != null) {
                    val constructor = holderClass!!.viewHolderClass.getConstructor(
                        View::class.java,
                        OnItemClickCallback::class.java
                    )
                    constructor.newInstance(view, onItemClickCallback) as BaseViewHolder
                } else {
                    val constructor = holderClass!!.viewHolderClass.getConstructor(
                        View::class.java
                    )
                    constructor.newInstance(view) as BaseViewHolder
                }
            }
        }
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        when {
            viewHolder.mBinding != null -> viewHolder.bind(
                getItem(position)!!, itemCount
            )
            else -> viewHolder.draw(
                getItem(position)!!
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        holderClass = getHolderClass(position)
        return holderClass!!.layoutResId //getItem(position)?.itemViewType() ?: 0
    }

    private fun getHolderClass(position: Int) : HolderClass =
        getItem(position)?.listItemType!!

    companion object {
        private val DEFAULT_DIFF = object : DiffUtil.ItemCallback<Listable>() {
            override fun areItemsTheSame(oldItem: Listable, newItem: Listable): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: Listable, newItem: Listable): Boolean {
                return false
            }
        }
    }
}
