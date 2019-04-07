package com.example.sunmoviedb.data.response

import com.example.sunmoviedb.data.Genre
import com.google.gson.annotations.SerializedName

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)
