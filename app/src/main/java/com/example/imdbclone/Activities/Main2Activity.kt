package com.example.imdbclone.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.imdbclone.*
import com.example.imdbclone.Adapters.TmdbAdapter
import com.example.imdbclone.Adapters.TmdbAdapter2
import com.example.imdbclone.Others.GithubService
import com.example.imdbclone.Others.Tmdb2
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.backdroplayout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofitClient.create(GithubService::class.java)

    //val snapitemhelper = SnapItemHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        setSupportActionBar(toolbar1)

        //startActivity(Intent(this,MainActivity::class.java))
        /*dummyEditTextFocus.requestFocus()
        dummyEditTextFocus.isFocusableInTouchMode = true*/

        //Retrofit
        service.nowShowing().enqueue(object : Callback<Tmdb2> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed, check your internet connection !!!"
            }
            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@Main2Activity, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter =
                            TmdbAdapter(this@Main2Activity, response.body()!!.results)
                    val snapItemHelper = PagerSnapHelper()
                    snapItemHelper.attachToRecyclerView(rview)
                    progressBar.visibility = View.GONE
                }
            }
        })

        service.popularMovies().enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview2.layoutManager = LinearLayoutManager(this@Main2Activity, LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter =
                            TmdbAdapter2(this@Main2Activity, response.body()!!.results)

                }
            }
        })
        service.upcoming().enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview3.layoutManager = LinearLayoutManager(this@Main2Activity, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter =
                            TmdbAdapter(this@Main2Activity, response.body()!!.results)
                    val snapItemHelper = PagerSnapHelper()
                    snapItemHelper.attachToRecyclerView(rview3)
                }
            }
        })
        service.toprated().enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
                //et.visibility = "gone".toInt()
            }
            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview4.layoutManager = LinearLayoutManager(this@Main2Activity, LinearLayoutManager.HORIZONTAL,false)
                    rview4.adapter =
                            TmdbAdapter2(this@Main2Activity, response.body()!!.results)
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

        viewAll1.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","nowshowing")
            startActivity(a)
            //startActivity(Intent(this@Main2Activity,ViewAll::class.java))
        }
        viewAll2.setOnClickListener {
            /*val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","popular")
            startActivity(a)*/
            startActivity(Intent(this@Main2Activity,ViewAll::class.java).putExtra("category","popular"))
        }
        viewAll3.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","upcoming")
            startActivity(a)
            //startActivity(Intent(this@Main2Activity,ViewAll::class.java))
        }
        viewAll4.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","toprated")
            startActivity(a)
            //startActivity(Intent(this@Main2Activity,ViewAll::class.java))
        }


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
                val a = Intent(this@Main2Activity, Search::class.java)
                a.putExtra("Search Text", query)
                if (query != "") {
                    startActivity(a)
                } else {
                    Toast.makeText(this@Main2Activity, "Write something in search bar !!!", Toast.LENGTH_SHORT)
                        .show()
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
                Toast.makeText(this,"Profile will be available after SignIn/SignOut part is completed.",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_movies -> {
                //startActivity(Intent(this,Main2Activity::class.java))
            }
            R.id.nav_tvShows -> {
                startActivity(Intent(this,TVShowsActivity::class.java))
            }
            R.id.nav_favourite -> {
                Toast.makeText(this,"Feature Coming Soon !!!",Toast.LENGTH_SHORT).show()
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
