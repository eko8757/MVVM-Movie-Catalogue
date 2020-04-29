package com.example.moviemvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemvvm.R
import com.example.moviemvvm.data.model.DiscoverMovie
import com.example.moviemvvm.utils.Constant
import com.example.moviemvvm.utils.year
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter(val context: Context, val listener: (DiscoverMovie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewModel>() {

    private var movies: MutableList<DiscoverMovie> = mutableListOf()

    fun setMovies(movie: List<DiscoverMovie>) {
        movies.clear()
        movies.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewModel {
        return MovieViewModel(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewModel, position: Int) {
        holder.bindData(movies[position], listener)
    }

    class MovieViewModel(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: DiscoverMovie, listener: (DiscoverMovie) -> Unit) {
            Picasso.get().load(Constant.BASE_IMAGE_URL + data.poster).into(itemView.imgPoster)
            itemView.tvTitle.text = data.title
            itemView.tvYear.text = data.releaseDate.year()
            itemView.tvDescription.text = data.description
            itemView.ratingBar.rating = data.vote/2

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}