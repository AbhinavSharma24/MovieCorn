package com.example.imdbclone.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.DetailsTv
import com.example.imdbclone.Others.Searchdetails
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.searchlayout.view.*

class SearchAdapterTv(val context: Context, private val arrayList: ArrayList<Searchdetails>)
    : RecyclerView.Adapter<SearchAdapterTv.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.searchlayout, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: Searchdetails? =null
        init{
            itemView.setOnClickListener {
                val l= Intent(context, DetailsTv::class.java)
                l.putExtra("ID",currentuser!!.id)
                context.startActivity(l)
            }
        }

        fun bind(user: Searchdetails, position: Int) {
            this.currentuser = user
            with(itemView) {
                titletv.text = user.name
                releasetv.text = "First Air Date: "+user.first_air_date
                overviewtv.text="\n"+user.overview
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img)

            }

        }
    }
}