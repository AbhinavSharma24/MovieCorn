package com.example.imdbclone.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.Details
import com.example.imdbclone.Others.TmdbResponse
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_all_layout.view.*

class ViewAllAdapter( val context: Context, private val arrayList: ArrayList<TmdbResponse>)
    : RecyclerView.Adapter<ViewAllAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.view_all_layout, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: TmdbResponse? = null
        init {
            itemView.setOnClickListener {
                val detail= Intent(context,Details::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        fun bind(user: TmdbResponse, position: Int) {
            this.currentuser = user
            with(itemView) {
                name.text = user.title
                rating.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).fit().centerCrop().into(imgViewAll)
            }
        }
    }
}