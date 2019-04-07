package com.example.sunmoviedb.data

import com.google.gson.annotations.SerializedName

/**
 * Created on 2019-04-25 by Sang
 * Description:
 **/
data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)
