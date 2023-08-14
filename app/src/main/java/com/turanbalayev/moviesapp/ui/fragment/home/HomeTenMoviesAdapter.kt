package com.turanbalayev.moviesapp.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.MovieItemBinding
import com.turanbalayev.moviesapp.model.Movie
import com.turanbalayev.moviesapp.model.MovieResult
import com.turanbalayev.moviesapp.util.Constant.BASE_URL_IMAGE

class HomeTenMoviesAdapter:RecyclerView.Adapter<HomeTenMoviesAdapter.TenMoviesViewHolder>() {

    inner class TenMoviesViewHolder(val binding:MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieResult){
            binding.movieItem = movie

/*            binding.textViewMovieScore.text = movie.voteAverage.toString()
            Glide.with(binding.root)
                .load(BASE_URL_IMAGE + movie.posterPath)
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .into(binding.imgCover);*/

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenMoviesViewHolder {
        val layout = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TenMoviesViewHolder(layout)
    }

    override fun getItemCount(): Int =  differ.currentList.size

    override fun onBindViewHolder(holder: TenMoviesViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }


}