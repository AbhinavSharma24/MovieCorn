package com.example.imdbclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imdbclone.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.app_bar_main.*

class SignInActivity : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        setSupportActionBar(toolbar1)

        dummy.requestFocus()
        dummy.isFocusableInTouchMode = true

        signupbtn.setOnClickListener {
            startActivity(Intent(this@SignInActivity,SignUpActivity::class.java))
        }

        signinbtn.setOnClickListener {
            if (email.editText?.text.isNullOrEmpty()) {
                email.isErrorEnabled = true
                email.error = "Cannot be Empty"
            } else if (password.editText?.text.isNullOrEmpty()) {
                password.isErrorEnabled = true
                password.error = "Cannot be Empty"
            } else {
                auth.createUserWithEmailAndPassword(
                    email.editText?.text.toString(),
                    password.editText?.text.toString()
                ).addOnCompleteListener {
                    signinbtn.isEnabled = false
                }.addOnSuccessListener {
                    signinbtn.isEnabled = true
                    Toast.makeText(this, "Account Created Succesfully", Toast.LENGTH_LONG).show()

                }.addOnFailureListener {
                    signinbtn.isEnabled = true
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    if (it.localizedMessage.contains("use", true)) {
                        //loginUser()
                    }
                }
            }
        }

    }
}

