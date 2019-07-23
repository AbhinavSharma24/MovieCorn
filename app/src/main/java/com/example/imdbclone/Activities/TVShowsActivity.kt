package com.example.imdbclone.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.imdbclone.Adapters.*
import com.example.imdbclone.Others.GithubService
import com.example.imdbclone.Others.TmdbTv
import com.example.imdbclone.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TVShowsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/tv/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofitClient.create(GithubService::class.java)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshows)
        setSupportActionBar(toolbar1)

        //Retrofit
        service.airingToday().enqueue(object : Callback<TmdbTv> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<TmdbTv>, t: Throwable) {
                tv.text="Loading failed, check your internet connection !!!"
            }
            override fun onResponse(
                call: Call<TmdbTv>,
                response: Response<TmdbTv>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@TVShowsActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter =
                            TmdbAdaptorTv(this@TVShowsActivity, response.body()!!.results)
                    val snapItemHelper = PagerSnapHelper()
                    snapItemHelper.attachToRecyclerView(rview)
                    progressBar.visibility = View.GONE
                }
            }
        })
        service.onTheAir().enqueue(object : Callback<TmdbTv> {
            override fun onFailure(call: Call<TmdbTv>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<TmdbTv>,
                response: Response<TmdbTv>
            ) {
                runOnUiThread {
                    rview2.layoutManager = LinearLayoutManager(this@TVShowsActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter =
                            TmdbAdaptorTv2(this@TVShowsActivity, response.body()!!.results)

                }
            }
        })
        service.popularTv().enqueue(object : Callback<TmdbTv> {
            override fun onFailure(call: Call<TmdbTv>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<TmdbTv>,
                response: Response<TmdbTv>
            ) {
                runOnUiThread {
                    rview3.layoutManager = LinearLayoutManager(this@TVShowsActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter =
                            TmdbAdaptorTv(this@TVShowsActivity, response.body()!!.results)
                    val snapItemHelper = PagerSnapHelper()
                    snapItemHelper.attachToRecyclerView(rview3)
                }
            }
        })
        service.topRatedTv().enqueue(object : Callback<TmdbTv> {
            override fun onFailure(call: Call<TmdbTv>, t: Throwable) {
                tv.text="Loading failed!"
                //et.visibility = "gone".toInt()
            }
            override fun onResponse(
                call: Call<TmdbTv>,
                response: Response<TmdbTv>
            ) {
                runOnUiThread {
                    rview4.layoutManager = LinearLayoutManager(this@TVShowsActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview4.adapter =
                            TmdbAdaptorTv2(this@TVShowsActivity, response.body()!!.results)
                }
            }
        })
        /*searchbutton.setOnClickListener {
            val a = Intent(this,Search::class.java)
            a.putExtra("Search Text",et.text.toString())
            if(et.text.toString() != "") {
                startActivity(a)
            }else{
                Toast.makeText(this,"Write something in search bar !!!",Toast.LENGTH_SHORT).show()
            }
        }*/


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar1,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout?.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView : SearchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val a = Intent(this@TVShowsActivity, SearchTv::class.java)
                a.putExtra("Search Text", query)
                if (query != "") {
                    startActivity(a)
                } else {
                    Toast.makeText(this@TVShowsActivity, "Write something in search bar !!!", Toast.LENGTH_SHORT).show()
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                Toast.makeText(this,"Profile will be available after SignIn/SignOut part is completed.", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_movies -> {
                startActivity(Intent(this,Main2Activity::class.java))
            }
            R.id.nav_tvShows -> {
                //startActivity(Intent(this,TVShowsActivity::class.java))
            }
            R.id.nav_favourite -> {
                Toast.makeText(this,"Feature Coming Soon !!!", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this,FavouriteActivity::class.java))
            }
            R.id.nav_about -> {
                startActivity(Intent(this,AboutActivity::class.java))
            }
            R.id.nav_contactUs -> {
                val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: abhinavsharma2499@gmail.com"))
                i.putExtra(Intent.EXTRA_SUBJECT,"Regarding IMDb Clone")
                startActivity(Intent.createChooser(i,"Send Email"))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
