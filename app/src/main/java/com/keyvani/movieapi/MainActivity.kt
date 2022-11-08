package com.keyvani.movieapi

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keyvani.MoviesAdapter
import com.keyvani.movieapi.databinding.ActivityMainBinding
import com.keyvani.movieapi.model.ResponseMoviesList
import com.keyvani.movieapi.server.ApiClient
import com.keyvani.movieapi.server.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }
    private val api: ApiServices by lazy {
        ApiClient().getClient().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            pbMovieLoader.visibility = View.VISIBLE
            val callMoviesApi = api.moviesList(1)
            callMoviesApi.enqueue(object : Callback<ResponseMoviesList> {
                override fun onResponse(call: Call<ResponseMoviesList>, response: Response<ResponseMoviesList>) {
                    pbMovieLoader.visibility = View.GONE
                    if (response.isSuccessful) {
                        response.body()?.let { itBody ->
                            itBody.data?.let { itData ->
                                if (itData.isNotEmpty()) {
                                    moviesAdapter.differ.submitList(itData)
                                    rvMovies.apply {
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                        adapter = moviesAdapter
                                    }

                                }
                            }

                        }

                    }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    pbMovieLoader.visibility = View.GONE
                    Log.e("onFailure", "Err:${t.message}")
                }

            })
        }


    }


}