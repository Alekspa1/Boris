package com.example.boris


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boris.APIClass.FilmInfo
import com.example.boris.APIClass.MoviesClass
import com.example.boris.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity(),
    CustomRecyclerAdapter.Listener { // тут я добавил листенер
    lateinit var binding: ActivityRegisterBinding
    private lateinit var adapter: CustomRecyclerAdapter
    val MoviesList = ArrayList<MoviesClass>() // Создаем список

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val recyclerView = binding.recyclerView // Инициализируем Recycler View
        recyclerView.layoutManager = LinearLayoutManager(this) // Делаем вид(таблицей)

        for (i in 0..5) { // временное решение пока не научусь пользоваться корутинами
            val apiInterface = ApiInterface.create().getMovies() // Инициализируем Api интерфейс
            apiInterface.enqueue(object : Callback<MoviesClass> {

                override fun onResponse(
                    call: Call<MoviesClass>,
                    response: Response<MoviesClass>
                ) { // Создаем функцию на поулчение результата API
                    val data = response.body() // Создаем переменную результата
                    if (data != null) { // Проверка на NULL
                        MoviesList.add(data)
                        adapter = CustomRecyclerAdapter(
                            baseContext,
                            MoviesList,
                            this@MoviesActivity
                        ) // Инициализируем адаптер
                        recyclerView.adapter = adapter // Подключаем адаптер
                    }
                }

                override fun onFailure(call: Call<MoviesClass>, t: Throwable) {
                    Log.d("MyLog", "Не успешно")
                }
            })
        }

    } // заканчивается onCreate

    override fun onBackPressed() { // Кнопка назад

        onBackPressedDispatcher.onBackPressed() // with this line
        this.finishAffinity() // выход из приложения
    } // заканчивается кнопка назад

    override fun onClick(movie: MoviesClass) { // функция для листенера
        val i = Intent(this@MoviesActivity, FilmActivity::class.java)
        i.putExtra("film", movie.id)
        startActivity(i)

    }
}



