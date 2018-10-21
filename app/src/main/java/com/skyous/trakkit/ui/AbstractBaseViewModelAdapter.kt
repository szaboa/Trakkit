package com.skyous.trakkit.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.RequestManager
import com.skyous.trakkit.R
import com.skyous.trakkit.TrakkitApplication
import javax.inject.Inject

/**
 * TODO
 *
 * @author Arnold Szabo
 * @since 10/20/2018
 *
 */
abstract class AbstractBaseViewModelAdapter(private val requestManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @Inject
    lateinit var screenConfig: ScreenConfig

    private var items: List<BaseViewModel> = ArrayList()

    init {
        TrakkitApplication.getComponent().inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (BaseViewModel.Type.from(viewType)) {
            BaseViewModel.Type.SERIES -> {
                val viewHolder = SeriesViewHolder(requestManager, LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false))
                viewHolder.coverImageView.layoutParams.width = screenConfig.getTileSize().width
                viewHolder.coverImageView.layoutParams.height = screenConfig.getTileSize().height
                return viewHolder
            }
            BaseViewModel.Type.ACTOR -> ActorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false))
            BaseViewModel.Type.GENRE -> GenreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder).bind(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].getType().type

    /**
     * TODO
     */
    abstract fun getLayoutManager(): RecyclerView.LayoutManager

    /**
     * TODO
     */
    fun setItemList(items: List<BaseViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    /**
     * TODO
     */
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: BaseViewModel)
    }

    /**
     * TODO
     */
    class SeriesViewHolder(private val requestManager: RequestManager, itemView: View) : BaseViewHolder(itemView) {
        @BindView(R.id.titleTextView)
        lateinit var titleTextView: TextView

        @BindView(R.id.coverImageView)
        lateinit var coverImageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bind(item: BaseViewModel) {
            (item as SeriesViewModel).let {
                titleTextView.text = it.title
                coverImageView.load(requestManager, "https://vignette.wikia.nocookie.net/thecw/images/5/52/The_100_%28CW%29_poster.jpg/revision/latest?cb=20170916001901")
            }
        }
    }

    /**
     * TODO
     */
    class ActorViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseViewModel) {

        }
    }

    /**
     * TODO
     */
    class GenreViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseViewModel) {

        }
    }
}