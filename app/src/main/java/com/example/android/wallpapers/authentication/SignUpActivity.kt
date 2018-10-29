package com.example.android.wallpapers.authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.wallpapers.R
import com.example.android.wallpapers.utilities.AuthUtil
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setSupportActionBar(sign_up_toolbar)
        supportActionBar?.title = "Sign Up"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sign_up_button.setOnClickListener {
            Toast.makeText(this, "Signing UP", Toast.LENGTH_SHORT).show()
            AuthUtil.signUpWithEmail(sign_up_email_edittext.text.toString(), sign_up_password_edittext.text.toString(), this)
        }
    }
}
