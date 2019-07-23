package com.example.imdbclone.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbclone.Adapters.MovieAdapter
import com.example.imdbclone.Adapters.MovieAdapter2
import com.example.imdbclone.Adapters.TmdbAdaptorTv2
import com.example.imdbclone.Others.*
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsTv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_tv)
        setSupportActionBar(toolbar33)
        supportActionBar?.title = "TV Show Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pos = intent.getIntExtra("ID", 55)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitClient.create(GithubService::class.java)

        service.overview(pos).enqueue(object : Callback<Overview> {
            override fun onFailure(call: Call<Overview>, t: Throwable) {
                tv.text="Loading failed!"
            }
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Overview>, response: Response<Overview>) {
                runOnUiThread {
                    Picasso.get().load("https://image.tmdb.org/t/p/original" + response.body()?.backdrop_path).fit().centerCrop().into(toolbarimage)
                    progressBar.visibility = View.GONE
                    if(response.body()?.backdrop_path == null){
                        Picasso.get().load("https://fasterthemes.com/demo/foodrecipespro-wordpress-theme/wp-content/themes/foodrecipespro/images/no-image.jpg").fit().centerCrop().into(toolbarimage)
                    }
                }
            }
        })

        service.overview(pos).enqueue(object : Callback<Overview> {
            override fun onFailure(call: Call<Overview>, t: Throwable) {
                tv.text="Loading failed!"
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<Overview>,
                response: Response<Overview>
            ) {
                runOnUiThread {
                    //toolbar.title=response.body()!!.original_title
                    tv.text="\n\n⭐ "+ response.body()!!.vote_average+"/10\n\nPlot :  "+ response.body()!!.overview +
                            "\n\nFirst Air Date : "+ response.body()!!.first_air_date + "\n\nStatus : " +
                            response.body()?.status + "\n\nGenres : "
                    for(i in 0 until response.body()!!.genres.size ){
                        tv.text= tv.text.toString() +response.body()!!.genres[i].name+", "
                    }
                    tv.text=tv.text.substring(0,tv.text.length-2) //to remove the extra comma
                }
            }
        })

        service.trailers(pos).enqueue(object : Callback<Trailerarray> {
            override fun onFailure(call: Call<Trailerarray>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<Trailerarray>,
                response: Response<Trailerarray>
            ) {
                runOnUiThread {
                    rview3.layoutManager = LinearLayoutManager(this@DetailsTv, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter =
                            MovieAdapter2(this@DetailsTv, response.body()!!.results)
                }
            }
        })

        service.castTv(pos).enqueue(object : Callback<Cast> {
            override fun onFailure(call: Call<Cast>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<Cast>,
                response: Response<Cast>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@DetailsTv, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = MovieAdapter(this@DetailsTv, response.body()!!.cast)
                }
            }
        })

        service.similarTv(pos).enqueue(object : Callback<TmdbTv> {
            override fun onFailure(call: Call<TmdbTv>, t: Throwable) {
                tv.text="Loading failed!"
            }

            override fun onResponse(
                call: Call<TmdbTv>,
                response: Response<TmdbTv>
            ) {
                runOnUiThread {
                    rview2.layoutManager = LinearLayoutManager(this@DetailsTv, LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter =
                            TmdbAdaptorTv2(this@DetailsTv, response.body()!!.results)
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
