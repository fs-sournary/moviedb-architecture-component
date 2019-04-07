package com.example.sunmoviedb.data

import com.google.gson.annotations.SerializedName

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
