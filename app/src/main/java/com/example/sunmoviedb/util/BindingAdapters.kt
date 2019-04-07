package com.example.sunmoviedb.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sunmoviedb.api.ApiConstants

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
object BindingAdapters {

    @BindingAdapter("urlImage", "circleImage", requireAll = false)
    @JvmStatic
    fun ImageView.loadUrlImage(url: String?, circleImage: Boolean) {
        url?.also {
            val actualUrl = ApiConstants.BASE_IMAGE_URL + url
            val requestBuilder = Glide.with(context).load(actualUrl)
            if (circleImage) {
                requestBuilder.apply(RequestOptions.circleCropTransform())
            }
            requestBuilder.into(this)
        }
    }
}
