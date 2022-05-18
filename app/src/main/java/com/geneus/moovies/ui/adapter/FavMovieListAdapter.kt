package com.geneus.moovies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geneus.moovies.R
import com.geneus.moovies.data.db.model.MovieModel

class FavMovieListAdapter(
    private val context: Context,
    private var movieList: List<MovieModel>,
    private val onSelect: (movie: MovieModel) -> Unit
) :
    RecyclerView.Adapter<FavMovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context)
                .inflate(R.layout.item_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMovieTitle.text = movieList[position].title
        holder.tvReleaseDate.text = movieList[position].releaseDate
        holder.tvAvgVote.text = movieList[position].voteAverage.toString()
        holder.tvVoteCount.text = "(${movieList[position].voteCount.toString()})"

        val genres = movieList[position].genreIds?.replace("(", "")?.replace(")", "")
        holder.tvGenre.text = genres

        holder.rlContainer.setOnClickListener {
            onSelect.invoke(
                movieList[position]
            )
        }

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/original${movieList[position].backdropPath}")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_img_default_placeholder)
            .into(holder.ivMoviePoster)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder internal constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        var tvGenre: TextView = itemView.findViewById(R.id.tvGenre)
        var tvReleaseDate: TextView = itemView.findViewById(R.id.tvReleaseDate)
        var tvAvgVote: TextView = itemView.findViewById(R.id.tvAvgVote)
        var tvVoteCount: TextView = itemView.findViewById(R.id.tvVoteCount)
        var rlContainer: RelativeLayout = itemView.findViewById(R.id.rlContainer)
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
    }


}