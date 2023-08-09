package com.turanbalayev.moviesapp.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.MovieItemBinding
import com.turanbalayev.moviesapp.model.Movie

class HomeTenMoviesAdapter:RecyclerView.Adapter<HomeTenMoviesAdapter.TenMoviesViewHolder>() {

    inner class TenMoviesViewHolder(val binding:MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.textViewMovieScore.text = movie.score.toString()
            binding.imgCover.setImageResource(R.drawable.avatar)
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
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