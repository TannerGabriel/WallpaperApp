package com.example.android.wallpapers.utilities

import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.android.wallpapers.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern


class AuthUtil{
    companion object {

        private var mAuth: FirebaseAuth? = null

        fun signUpWithEmail(email: String, password: String, context: Context){
            if(validatePassword(password)){
                mAuth = FirebaseAuth.getInstance()

                mAuth?.createUserWithEmailAndPassword(email, password)?.addOnSuccessListener {
                    val mFirestore = FirebaseFirestore.getInstance()
                    val user = HashMap<String, Any>()
                    user.put("uid",mAuth?.currentUser?.uid!! )

                    mFirestore.collection("users").document(mAuth?.currentUser?.uid!!).set(user).addOnSuccessListener {
                        println("Successfully added!")
                    }.addOnFailureListener {
                        println("Error: $it")
                    }

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }else{
                println("Wrong password!")
            }
        }

        fun signInWithEmail(email: String, password: String, context: Context){
            mAuth = FirebaseAuth.getInstance()

            mAuth?.signInWithEmailAndPassword(email, password)?.addOnSuccessListener {
                println("Successfully signed in!")
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
            }
        }

        fun logout(){
            mAuth = FirebaseAuth.getInstance()
            mAuth?.signOut()
        }

        private fun validatePassword(password: String): Boolean{
            val PASSWORD_PATTERN = "[a-zA-Z0-9!@#$]{8,24}"
            val pattern = Pattern.compile(PASSWORD_PATTERN)
            val matcher = pattern.matcher(password)

            return matcher.matches()
        }
    }



}