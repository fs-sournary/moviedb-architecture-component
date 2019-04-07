package com.example.sunmoviedb.api

import com.example.sunmoviedb.data.DetailMovie
import com.example.sunmoviedb.data.response.CategoryMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
interface MovieApi {

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: Int): Single<DetailMovie>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Single<CategoryMovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<CategoryMovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Single<CategoryMovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page: Int): Single<CategoryMovieResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): Single<CategoryMovieResponse>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendationMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): Single<CategoryMovieResponse>
}
