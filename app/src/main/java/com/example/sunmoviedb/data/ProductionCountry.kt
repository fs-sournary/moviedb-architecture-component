package com.example.sunmoviedb.data

import com.google.gson.annotations.SerializedName

/**
 * Created on 2019-04-25 by Sang
 * Description:
 **/
class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)
