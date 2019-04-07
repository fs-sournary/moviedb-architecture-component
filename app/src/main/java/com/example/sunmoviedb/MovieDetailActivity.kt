package com.example.sunmoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

/**
 * Created in 4/23/19 by Sang
 * Description:
 */
class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        findNavController(R.id.movie_detail_host_fragment)
            .setGraph(R.navigation.navigation_movie_detail, intent.extras)
    }
}
