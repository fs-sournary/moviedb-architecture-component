package com.example.sunmoviedb.di

import com.example.sunmoviedb.BuildConfig
import com.example.sunmoviedb.api.ApiConstants
import com.example.sunmoviedb.api.DiscoverApi
import com.example.sunmoviedb.api.GenreApi
import com.example.sunmoviedb.api.MovieApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
val networkModule = module {
    single(StringQualifier("Logging")) { createLoggingInterceptor() }
    single(StringQualifier("Header")) { createHeaderInterceptor() }
    single {
        createOkHttpClient(get(StringQualifier("Logging")), get(StringQualifier("Header")))
    }
    single { createRetrofit(get()) }
    single { createMovieApi(get()) }
    single { createGenreApi(get()) }
    single { createDiscoverApi(get()) }
}

private fun createLoggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

private fun createHeaderInterceptor(): Interceptor =
    Interceptor {
        val request = it.request()
        val newUrl =
            request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val newRequest =
            request.newBuilder().url(newUrl).method(request.method(), request.body()).build()
        it.proceed(newRequest)
    }

private fun createOkHttpClient(
    loggingInterceptor: Interceptor, headerInterceptor: Interceptor
): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(10L, TimeUnit.SECONDS)
    .readTimeout(10L, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .addInterceptor(headerInterceptor)
    .build()

private fun createRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiConstants.BASE_URL)
        .client(client)
        .build()

private fun createMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

private fun createGenreApi(retrofit: Retrofit): GenreApi = retrofit.create(GenreApi::class.java)

private fun createDiscoverApi(retrofit: Retrofit): DiscoverApi =
    retrofit.create(DiscoverApi::class.java)
