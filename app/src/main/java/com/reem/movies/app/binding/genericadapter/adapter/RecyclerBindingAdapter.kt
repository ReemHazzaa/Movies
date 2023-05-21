package com.reem.movies.app.binding.genericadapter.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.reem.movies.app.binding.genericadapter.Listable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


object RecyclerBindingAdapter {

    @JvmStatic
    @BindingAdapter("submitData")
    fun <T : Listable> RecyclerView.submitData(items: List<T>?) {
        val adapter: GeneralListAdapter? = this.adapter as GeneralListAdapter?
        if (items != null && adapter != null) {
            //  val lifecycleOwner: LifecycleOwner = this.findViewTreeLifecycleOwner() as LifecycleOwner
            GlobalScope.launch {
                adapter.submitData(items)

            }
//            lifecycleOwner.lifecycleScope.launch {
//                adapter.submitData(items)
//            }
        }
    }


    @JvmStatic
    @BindingAdapter("submitPagerData")
    fun <T : Listable> ViewPager2.submitPagerData(items: List<T>?) {
        val adapter: GeneralListAdapter? = this.adapter as GeneralListAdapter?
        if (items != null && adapter != null) {
            val lifecycleOwner: LifecycleOwner = this.findViewTreeLifecycleOwner() as LifecycleOwner
            lifecycleOwner.lifecycleScope.launch {
                adapter.submitData(items)
            }
        }
    }

}