package com.example.moviemvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemvvm.R
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.utils.Constant
import com.example.moviemvvm.utils.year
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class TvAdapter(val context: Context, val listener: (DiscoverTv) -> Unit) : RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    private var mTvShows : MutableList<DiscoverTv> = mutableListOf()

    fun setTvShows(tvShows: List<DiscoverTv>) {
        mTvShows.clear()
        mTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return mTvShows.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mTvShows[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: DiscoverTv, listener: (DiscoverTv) -> Unit) {
            Picasso.get().load(Constant.BASE_IMAGE_URL + data.poster).into(itemView.imgPoster)
            itemView.tvTitle.text = data.title
            itemView.tvYear.text = data.firstAirDate.year()
            itemView.tvDescription.text = data.description
            itemView.ratingBar.rating = data.rating/2

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}