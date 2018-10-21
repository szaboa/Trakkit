package com.skyous.trakkit.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.RequestManager
import com.skyous.trakkit.R

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/19/2018
 *
 */
class VerticalComponentListAdapter<T : BaseViewModel>(val requestManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<BaseVerticalComponent<T>> = ArrayList()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (BaseVerticalComponent.Type.from(viewType)) {
            BaseVerticalComponent.Type.STRIPE -> {
                val viewHolder = StripeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_stripe, parent, false), requestManager)
                viewHolder.stripeView.setRecycledViewPool(viewPool)
                return viewHolder
            }
            BaseVerticalComponent.Type.BANNER -> BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_stripe, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder).bind(items[holder.adapterPosition])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType().type;
    }


    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * TODO
     */
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun <T : BaseViewModel> bind(item: BaseVerticalComponent<T>)
    }

    /**
     * TODO
     */
    fun setItemList(items: List<BaseVerticalComponent<T>>) {
        this.items = items
        notifyDataSetChanged()
    }

    /**
     * TODO
     */
    class StripeViewHolder(itemView: View, requestManager: RequestManager) : BaseViewHolder(itemView) {
        @BindView(R.id.titleTextView)
        lateinit var titleTextView: TextView

        @BindView(R.id.stripeView)
        lateinit var stripeView: HorizontalStripeView

        init {
            ButterKnife.bind(this, itemView)
            stripeView.initialize(requestManager)
        }

        override fun <T : BaseViewModel> bind(item: BaseVerticalComponent<T>) {
            (item as HorizontalStripe).let {
                titleTextView.text = it.title
                stripeView.horizontalStripeAdapter.setItemList(item.list)
            }
        }
    }

    /**
     * TODO
     */
    class BannerViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun <T : BaseViewModel> bind(item: BaseVerticalComponent<T>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}