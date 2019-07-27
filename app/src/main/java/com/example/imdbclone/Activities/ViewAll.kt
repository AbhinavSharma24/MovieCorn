package com.example.imdbclone.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Adapters.ViewAllAdapter
import com.example.imdbclone.Others.GithubService
import com.example.imdbclone.Others.Tmdb2
import com.example.imdbclone.R
import kotlinx.android.synthetic.main.activity_view_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewAll : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)

        setSupportActionBar(toolbarViewAll)
        supportActionBar?.title = "Movies"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitClient.create(GithubService::class.java)

        val p = intent.getStringExtra("category")

        when(p) {
            "nowshowing" ->
                service.nowShowing().enqueue(object : Callback<Tmdb2> {
                    override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                        //tv.text="Loading failed!"
                        progressBar.visibility = View.GONE
                    }

                    override fun onResponse(
                        call: Call<Tmdb2>,
                        response: Response<Tmdb2>
                    ) {
                        runOnUiThread {
                            rvViewAll.layoutManager = GridLayoutManager(this@ViewAll, 3, RecyclerView.VERTICAL, false)
                            rvViewAll?.adapter = ViewAllAdapter(this@ViewAll, response.body()!!.results)
                            progressBar.visibility = View.GONE
                        }
                    }
                })

            "popular" ->
                service.popularMovies().enqueue(object : Callback<Tmdb2> {
                    override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                        //tv.text="Loading failed!"
                        progressBar.visibility = View.GONE
                    }

                    override fun onResponse(
                        call: Call<Tmdb2>,
                        response: Response<Tmdb2>
                    ) {
                        runOnUiThread {
                            rvViewAll.layoutManager = GridLayoutManager(this@ViewAll, 3, RecyclerView.VERTICAL, false)
                            rvViewAll?.adapter = ViewAllAdapter(this@ViewAll, response.body()!!.results)
                            progressBar.visibility = View.GONE
                        }
                    }
                })

            "upcoming" ->
                service.upcoming().enqueue(object : Callback<Tmdb2> {
                    override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                        //tv.text="Loading failed!"
                        progressBar.visibility = View.GONE
                    }

                    override fun onResponse(
                        call: Call<Tmdb2>,
                        response: Response<Tmdb2>
                    ) {
                        runOnUiThread {
                            rvViewAll.layoutManager = GridLayoutManager(this@ViewAll, 3, RecyclerView.VERTICAL, false)
                            rvViewAll?.adapter = ViewAllAdapter(this@ViewAll, response.body()!!.results)
                            progressBar.visibility = View.GONE
                        }
                    }
                })

            "toprated" ->
                service.toprated().enqueue(object : Callback<Tmdb2> {
                    override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                        //tv.text="Loading failed!"
                        progressBar.visibility = View.GONE
                    }

                    override fun onResponse(
                        call: Call<Tmdb2>,
                        response: Response<Tmdb2>
                    ) {
                        runOnUiThread {
                            rvViewAll.layoutManager = GridLayoutManager(this@ViewAll, 3, RecyclerView.VERTICAL, false)
                            rvViewAll?.adapter = ViewAllAdapter(this@ViewAll, response.body()!!.results)
                            progressBar.visibility = View.GONE
                        }
                    }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
