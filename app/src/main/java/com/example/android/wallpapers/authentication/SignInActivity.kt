package com.example.android.wallpapers.authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.android.wallpapers.R
import com.example.android.wallpapers.utilities.AuthUtil
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setSupportActionBar(sign_in_toolbar)
        supportActionBar?.title = "Sign In"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        need_account_textview.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        sign_in_button.setOnClickListener {
            if(!TextUtils.isEmpty(sign_in_email_edittext.text.toString()) && !TextUtils.isEmpty(sign_in_password_edittext.text.toString())){
                AuthUtil.signInWithEmail(sign_in_email_edittext.text.toString(), sign_in_password_edittext.text.toString(), this)
                Toast.makeText(this, "Signing in", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
