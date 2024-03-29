package com.cubos.cine.scenes.home.onTheaters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cubos.cine.R
import com.cubos.cine.models.Movie
import com.cubos.cine.networking.ApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_card.view.*

class OnTheatersRecyclerAdapter(private val onClick: ((Movie, View?) -> Unit)) :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                (oldItem == newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_on_theaters_card, parent,
                false
            )
        ) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            getItem(position)?.let { movie ->
                val posterUrl = ApiService.posterBaseURL + ApiService.posterBaseSize + movie.poster_path
                Picasso.get().load(posterUrl).into(moviePoster)
                setOnClickListener {
                    onClick(movie, cardView)
                }
            }
        }
    }

}