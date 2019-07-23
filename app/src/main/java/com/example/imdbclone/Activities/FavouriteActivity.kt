package com.example.imdbclone.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdbclone.R
import kotlinx.android.synthetic.main.activity_about.*

class FavouriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        setSupportActionBar(abouttoolbar)
        supportActionBar?.title = "Favourites"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
