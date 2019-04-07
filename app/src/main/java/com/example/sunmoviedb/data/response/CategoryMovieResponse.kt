package com.example.sunmoviedb.data.response

import com.example.sunmoviedb.data.Dates
import com.example.sunmoviedb.data.CategoryMovie
import com.google.gson.annotations.SerializedName

/**
 * Created on 4/3/19 by Sang
 * Description:
 **/
data class CategoryMovieResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<CategoryMovie>? = null,
    @SerializedName("total_pages")
    val totalPage: Int? = null,
    @SerializedName("total_results")
    val totalResult: Int? = null
)
