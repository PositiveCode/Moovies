package com.geneus.moovies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geneus.moovies.R
import com.geneus.moovies.data.api.model.Movie

class MovieListAdapter(
    private val context: Context,
    private var movieList: ArrayList<Movie>,
    private val onSelect: (movie: Movie) -> Unit
) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context)
                .inflate(R.layout.item_now_playing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMovieTitle.text = movieList[position].title
        holder.rlContainer.setOnClickListener {
            onSelect.invoke(
                movieList[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder internal constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        var rlContainer: RelativeLayout = itemView.findViewById(R.id.rlContainer)
    }

    fun setItems(newMovieList: ArrayList<Movie>) {
        movieList = newMovieList
    }
}