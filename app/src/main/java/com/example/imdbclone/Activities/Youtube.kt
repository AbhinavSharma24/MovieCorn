package com.example.imdbclone.Activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.imdbclone.Others.GithubService
import com.example.imdbclone.Others.Trailers
import com.example.imdbclone.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import kotlinx.android.synthetic.main.activity_youtube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Youtube : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        val path=intent.getStringExtra("path")

        youtube_player_fragment.initialize("AIzaSyCG9a5fq1k2JZLu-mZMlOzAyaMu5htRsfw",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, wasRestored: Boolean) {
                        if(!wasRestored){
                            youTubePlayer.loadVideo(path)
                            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                            progressBar.visibility = View.GONE
                        }
                    }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult) {
                        Toast.makeText(this@Youtube,youTubeInitializationResult.toString(),Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }
            })
    }
}



//Previous Code

/*class Youtube : AppCompatActivity() , YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        setSupportActionBar(toolbar33)
        supportActionBar?.title = "Movie Trailer"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //val playerFragment = fragmentManager.findFragmentById(R.id.youtube_player_fragment) as YouTubePlayerFragment
        youtube_player_fragment.initialize("AIzaSyCG9a5fq1k2JZLu-mZMlOzAyaMu5htRsfw", this@Youtube)
    }

    //private val playerFragment: YouTubePlayerFragment? = null
    private var mPlayer: YouTubePlayer? = null
    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider, player: YouTubePlayer,
        wasRestored: Boolean
        ) {
        //Enables automatic control of orientation
        player.fullscreenControlFlags = YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION

        //Show full screen in landscape mode always
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE)

        //System controls will appear automatically
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI)

        val pos = intent.getIntExtra("ID", 10)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitClient.create(GithubService::class.java)

        service.trailersYT(pos).enqueue(object : Callback<Trailers> {
            override fun onFailure(call: Call<Trailers>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<Trailers>,
                response: Response<Trailers>
            ) {
                runOnUiThread {
                    if (!wasRestored) {
                        player.loadVideo("https://www.youtube.com/watch?v=" + response.body()?.key)
                        //player.cueVideo("9rLZYyMbJic")
                    } else {
                        player.play()
                    }
                    progressBar.visibility = View.GONE
                }
            }
        })


        //val arrayList: ArrayList<Trailers>
        *//*val user : Trailers
        if (!wasRestored) {
            //player.cueVideo("9rLZYyMbJic");
            player.loadVideo("https://www.youtube.com/watch?v=" + user.key)
        } else {
            player.play()
        }*//*
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        errorReason: YouTubeInitializationResult
    ) {
        mPlayer = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}*/
