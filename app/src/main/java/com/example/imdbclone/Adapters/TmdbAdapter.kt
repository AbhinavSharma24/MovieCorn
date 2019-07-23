package com.example.imdbclone.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.Details
import com.example.imdbclone.R
import com.example.imdbclone.Others.TmdbResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.backdroplayout.view.*
import java.util.*

class TmdbAdapter( val context: Context, private val arrayList: ArrayList<TmdbResponse>)
    : RecyclerView.Adapter<TmdbAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.backdroplayout, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentuser: TmdbResponse? = null
        private var currentposition = 0

        init {
            itemView.setOnClickListener {
                val detail= Intent(context, Details::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(user: TmdbResponse, position: Int) {
            this.currentuser = user
            this.currentposition = position
            with(itemView) {
                titletv.text = user.title
                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/original" + user.backdrop_path).fit().centerCrop().into(img)

                if(user.backdrop_path == null){
                    Picasso.get().load("https://fasterthemes.com/demo/foodrecipespro-wordpress-theme/wp-content/themes/foodrecipespro/images/no-image.jpg").fit().centerCrop().into(img)
                }

                ib1blank.setOnClickListener {
                    ib1filled.visibility = View.VISIBLE
                    ib1blank.visibility = View.GONE
                }
                ib1filled.setOnClickListener {
                    ib1filled.visibility = View.GONE
                    ib1blank.visibility = View.VISIBLE
                }
            }
        }

    }
}