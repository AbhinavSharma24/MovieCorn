package com.example.imdbclone.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.Details
import com.example.imdbclone.Others.Moviecast
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.posterlayout.view.*

class Moviecastadapter(val context: Context, private val arrayList: ArrayList<Moviecast>)
    : RecyclerView.Adapter<Moviecastadapter.GithubViewHolder>() {
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
        var currentuser: Moviecast? = null
        init {
            itemView.setOnClickListener {
                val detail= Intent(context, Details::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        fun bind(user: Moviecast, position: Int) {

            this.currentuser = user

            with(itemView) {
                titletv.text = user.title
                ratingtv.text = "As " + user.character
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
//

        }
    }
}
