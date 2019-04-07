package com.example.sunmoviedb.ui.globalsetting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sunmoviedb.util.SingleLiveEvent

/**
 * Create on 4/5/19 by Sang
 * Description:
 **/
class GlobalSettingViewModel : ViewModel() {

    private val _captionEvent = SingleLiveEvent<Any>()
    val captionEvent: LiveData<Any> = _captionEvent

    fun loadCaption() {
        _captionEvent.call()
    }
}
