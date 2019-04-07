package com.example.sunmoviedb.api

import com.example.sunmoviedb.data.response.GenreResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
interface GenreApi {

    @GET("genre/movie/list")
    fun getGenres(): Single<GenreResponse>
}
