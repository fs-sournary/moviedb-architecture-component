package com.example.sunmoviedb.api

import com.example.sunmoviedb.data.response.DiscoverMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
interface DiscoverApi {

    @GET("discover/movie")
    fun getMovieByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): Single<DiscoverMovieResponse>
}
