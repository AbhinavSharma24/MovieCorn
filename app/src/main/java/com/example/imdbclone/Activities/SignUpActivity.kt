package com.example.imdbclone.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdbclone.R
import kotlinx.android.synthetic.main.app_bar_main.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(toolbar1)

    }
}
