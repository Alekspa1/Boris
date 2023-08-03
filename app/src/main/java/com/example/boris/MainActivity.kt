package com.example.boris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.boris.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }
    private lateinit var database: DatabaseReference

    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build()
    )
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = Firebase.database.reference
        binding.apply { // тут внутри байдинга
            edAv.setOnClickListener { // кнопка
                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                signInLauncher.launch(signInIntent)
            }
            Picasso.get()
                .load("https://i.pinimg.com/originals/30/cc/26/30cc261ff359f3d516afbc5287245b3c.jpg")
                .into(imView)
        }

        } // onCreate

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {

            val authUser = FirebaseAuth.getInstance().currentUser
            val email = authUser?.email.toString()
            val uid = authUser?.displayName.toString()
            val fireBaseUser = User(email, uid)

            database.child("users").child(authUser?.uid.toString()).setValue(fireBaseUser)
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

} // заканчивается onCreate
