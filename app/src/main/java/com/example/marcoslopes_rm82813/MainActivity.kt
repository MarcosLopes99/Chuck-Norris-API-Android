package com.example.marcoslopes_rm82813

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.marcoslopes_rm82813.databinding.ActivityMainBinding
import com.example.marcoslopes_rm82813.models.Joke
import com.example.marcoslopes_rm82813.services.JokeConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRandom.setOnClickListener {
            getJoke()
        }
    }

    private fun getJoke() {

        JokeConnection().service.getJoke().enqueue(object: Callback<Joke> {

            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, getString(R.string.joke_not_found), Toast.LENGTH_SHORT).show()
                    return
                }

                response.body()?.let { joke ->
                    configureJoke(joke)
                } ?: run {
                    Toast.makeText(this@MainActivity, getString(R.string.joke_not_found), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Toast.makeText(this@MainActivity, getString(R.string.joke_not_found), Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun configureJoke(joke: Joke) {
        binding.tvJoke.text = joke.value
    }

}