package com.skyous.trakkit.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.skyous.trakkit.R
import com.skyous.trakkit.data.viewmodel.BaseViewModel
import com.skyous.trakkit.data.viewmodel.SeriesViewModel
import com.skyous.trakkit.ui.component.HorizontalStripe
import com.skyous.trakkit.ui.component.VerticalComponentListAdapter

/**
 * Class comment here
 *
 * @author Peter Abraham
 * @since 10/18/2018
 *
 */
class HomeFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        val list: ArrayList<SeriesViewModel> = ArrayList()
        for (i in 1..10) {
            list.add(SeriesViewModel("Title $i"))
        }

        val componentList: ArrayList<HorizontalStripe<BaseViewModel>> = ArrayList()
        for (i in 1..10) {
            componentList.add(HorizontalStripe("Stripe $i", list))
        }

        val adapter = VerticalComponentListAdapter<BaseViewModel>(Glide.with(this))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        adapter.setItemList(componentList)
    }
}
