package com.example.sunmoviedb.data

import com.google.gson.annotations.SerializedName

/**
 * Created on 4/3/19 by Sang
 * Description:
 **/
data class Dates(
    @SerializedName("maximum")
    val maximum: String? = null,
    @SerializedName("minimum")
    val minimum: String? = null
)
