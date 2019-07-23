package com.example.imdbclone.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Others.Trailers
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trailers.view.*

class TvAdaptor2(val context: Context, private val arrayList: ArrayList<Trailers>)
    : RecyclerView.Adapter<GithubViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder2 {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder2(
            inflater.inflate(
                R.layout.trailers,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder2, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }
}

class GithubViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: Trailers, position: Int) {

        with(itemView) {
            tv.text=user.name
            Picasso.get().load("https://img.youtube.com/vi/"+user.key+"/maxresdefault.jpg").fit().centerCrop().into(bt)
            bt.setOnClickListener {
                val j= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+user.key))
                context.startActivity(j)
            }
        }

    }

}