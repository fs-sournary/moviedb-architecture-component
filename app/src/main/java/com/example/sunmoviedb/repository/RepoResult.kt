package com.example.sunmoviedb.repository

import androidx.lifecycle.LiveData

/**
 * Created on 4/3/19 by Sang
 * Description:
 **/
class RepoResult<T>(
    val data: T,
    val networkState: LiveData<NetworkState>? = null,
    val initialState: LiveData<NetworkState>? = null,
    val retry: (() -> Unit)? = null
)
