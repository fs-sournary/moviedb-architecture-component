package com.example.sunmoviedb.extensions

import android.os.Build
import android.text.Html
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.sunmoviedb.data.Genre

/**
 * Created on 2019-04-20 by Sang
 * Description:
 **/
fun AppCompatActivity.setToolbarStyleTitle(@StringRes id: Int) {
    val title = getString(id)
    supportActionBar?.title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(title)
    }
}
