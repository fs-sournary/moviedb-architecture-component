package com.example.sunmoviedb.data.response

import com.example.sunmoviedb.data.DiscoverMovie
import com.google.gson.annotations.SerializedName

/**
 * Created on 2019-04-21 by Sang
 * Description:
 **/
data class DiscoverMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<DiscoverMovie>,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("total_pages")
    val totalPage: Int
)
