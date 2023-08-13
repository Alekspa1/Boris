package com.example.boris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.boris.APIClass.FilmInfo
import com.example.boris.APIClass.MoviesClass
import com.example.boris.databinding.ActivityFilmBinding
import com.squareup.picasso.Picasso

class FilmActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val film = intent.getSerializableExtra("film" ) as FilmInfo
        binding.textView.text = film.name
        Picasso.get()
            .load(film.img)
            .into(binding.imageView)
    }
}