package com.example.boris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.boris.APIClass.FilmInfo
import com.example.boris.APIClass.MoviesClass
import com.example.boris.databinding.ActivityFilmBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val film = intent.getIntExtra("film", 0)
//        binding.textView.text = film.name
//        val idT = "ID: ${film.id.toString()}"
//        binding.textView2.text = idT
//        Picasso.get()
//            .load(film.img)
//            .into(binding.imageView)

        val apiInterface = ApiInterface.create().getMovieInfo(film) // Инициализируем Api интерфейс
        apiInterface.enqueue(object : Callback<MoviesClass> {

            override fun onResponse(
                call: Call<MoviesClass>,
                response: Response<MoviesClass>
            ) { // Создаем функцию на поулчение результата API
                val data = response.body() // Создаем переменную результата
                if (data != null) { // Проверка на NULL
                    binding.textView.text = data.name
                    val idT = "ID: ${data.id}"
                    binding.textView2.text = idT

                    Picasso.get()
                        .load(data.poster.url)
                        .into(binding.imageView)
                }
            }

            override fun onFailure(call: Call<MoviesClass>, t: Throwable) {
                Log.d("MyLog", "Не успешно")
            }
        })
    }
}