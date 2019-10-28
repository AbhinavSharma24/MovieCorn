package com.example.imdbclone.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Adapters.SearchAdapter
import com.example.imdbclone.Adapters.SearchAdapterTv
import com.example.imdbclone.Others.GithubService
import com.example.imdbclone.Others.Searcharray
import com.example.imdbclone.R
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("Registered")
class SearchTv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar22)
        supportActionBar?.title = "Search Results"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val text=intent.getStringExtra("Search Text")
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofitClient.create(GithubService::class.java)

            /*service.search(text).enqueue(object : Callback<Searcharray> {
                @SuppressLint("SetTextI18n")
                override fun onFailure(call: Call<Searcharray>, t: Throwable) {
                    tv.text = "Loading failed!"
                    tv.text = tv.text.toString() + t.cause.toString()
                }
                override fun onResponse(
                    call: Call<Searcharray>,
                    response: Response<Searcharray>
                ) {
                    runOnUiThread {
                        rview.layoutManager = LinearLayoutManager(this@SearchTv, RecyclerView.VERTICAL,false)
                        rview.adapter = SearchAdapter(this@SearchTv, response.body()!!.results)
                        progressBar.visibility = View.GONE
                    }
                }
            })*/

        service.searchtv(text).enqueue(object : Callback<Searcharray> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<Searcharray>, t: Throwable) {
                tv.text = "Loading failed!!!"
                tv.text = tv.text.toString() + t.cause.toString()
            }
            override fun onResponse(
                call: Call<Searcharray>,
                response: Response<Searcharray>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@SearchTv, RecyclerView.VERTICAL,false)
                    rview.adapter = SearchAdapterTv(this@SearchTv, response.body()!!.results)
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
