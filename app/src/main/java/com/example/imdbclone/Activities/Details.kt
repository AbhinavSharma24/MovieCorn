package com.example.imdbclone.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbclone.*
import com.example.imdbclone.Adapters.MovieAdapter
import com.example.imdbclone.Adapters.MovieAdapter2
import com.example.imdbclone.Adapters.TmdbAdapter2
import com.example.imdbclone.Others.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("Registered")
class Details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar33)
        supportActionBar?.title = "Movie Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pos = intent.getIntExtra("ID", 55)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
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
                    tv.text="\n\n⭐ "+ response.body()!!.vote_average+"/10\n\nPlot:  "+ response.body()!!.overview+"\n" +
                            "\nRelease Date: "+response.body()!!.release_date+"\n\nRuntime: "+
                            response.body()!!.runtime/60 +" hrs " +response.body()!!.runtime%60+" mins"+"\n\nGenres: "

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
                    rview3.layoutManager = LinearLayoutManager(this@Details, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter =
                            MovieAdapter2(this@Details, response.body()!!.results)
                }
            }
        })

        service.cast(pos).enqueue(object : Callback<Cast> {
            override fun onFailure(call: Call<Cast>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<Cast>,
                response: Response<Cast>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@Details, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = MovieAdapter(this@Details, response.body()!!.cast)
                }
            }
        })

        service.similarmovies(pos).enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
            }

            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview2.layoutManager = LinearLayoutManager(this@Details,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter =
                            TmdbAdapter2(this@Details, response.body()!!.results)
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
