package com.example.imdbclone.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.DetailsTv
import com.example.imdbclone.Others.TmdbTvResponse
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.posterlayout.view.*

class TmdbAdaptorTv2(val context: Context, private val arrayList: ArrayList<TmdbTvResponse>)
    : RecyclerView.Adapter<TmdbAdaptorTv2.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.posterlayout, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentuser: TmdbTvResponse? = null
        private var currentposition = 0
        init {
            itemView.setOnClickListener {
                val detail= Intent(context, DetailsTv::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(user: TmdbTvResponse, position: Int) {
            this.currentuser = user
            this.currentposition = position
            with(itemView) {
                titletv.text = user.name
                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).fit().centerCrop().into(img2)

                ib3blank.setOnClickListener {
                    ib3filled.visibility = View.VISIBLE
                    ib3blank.visibility = View.GONE
                }
                ib3filled.setOnClickListener {
                    ib3filled.visibility = View.GONE
                    ib3blank.visibility = View.VISIBLE
                }
            }
        }
    }

}