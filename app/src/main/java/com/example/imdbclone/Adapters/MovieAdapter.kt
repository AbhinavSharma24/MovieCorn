package com.example.imdbclone.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.CastDetails
import com.example.imdbclone.Others.MovieDetails
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profilelayout.view.*


class MovieAdapter(val context: Context, private val arrayList: ArrayList<MovieDetails>)
    : RecyclerView.Adapter<MovieAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.profilelayout, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: MovieDetails? =null
        init{
            itemView.setOnClickListener {
                val l = Intent(context, CastDetails::class.java)
                l.putExtra("castID",currentuser!!.id)
                context.startActivity(l)
            }
        }

        fun bind(user: MovieDetails, position: Int) {
            this.currentuser = user
            with(itemView) {
                charactertv.text = user.character
                nametv.text = user.name
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.profile_path).fit().centerCrop().into(img)
                if (user.profile_path == null) {
                    Picasso.get().load(
                        "https://wingslax.com/wp-content/uploads/2017/12/no-image-available.png"
                    ).into(img)
                }
            }
        }
    }
}





/*
class NowShowingAdapter(private val moviesList: ArrayList<Movies>, private val context: Context) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.itemView.apply {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.nowshowing_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
*/
