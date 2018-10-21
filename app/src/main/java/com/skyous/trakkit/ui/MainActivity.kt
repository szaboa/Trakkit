package com.skyous.trakkit.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.skyous.trakkit.R
import com.skyous.trakkit.TrakkitApplication
import java.util.*

class MainActivity : AppCompatActivity() {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

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
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.setItemList(componentList)

        TrakkitApplication.getComponent().inject(this)
    }
}
