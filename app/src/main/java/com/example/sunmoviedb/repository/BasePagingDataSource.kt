package com.example.sunmoviedb.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.sunmoviedb.api.ApiConstants

/**
 * Create on 4/7/19 by Sang
 * Description:
 **/
abstract class BasePagingDataSource<V>(
    private val isLoadBefore: Boolean = false, private val isLoadAfter: Boolean = false
) : PageKeyedDataSource<Int, V>() {

    var retry: (() -> Unit)? = null

    val initialState = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, V>
    ) {
        initialState.postValue(NetworkState.RUNNING)
        networkState.postValue(NetworkState.RUNNING)
        loadInitialFromSource(params, callback)
    }

    /**
     * Used for [BasePagingDataSource.loadInitial]
     */
    abstract fun loadInitialFromSource(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, V>
    )

    /**
     * Used when [loadInitial] success
     * Make sure that if you have to load before or start, you must complete startedPage correctly
     */
    fun handleLoadInitialSuccess(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, V>,
        data: List<V>?,
        startedPage: Int = ApiConstants.STARTED_PAGE
    ) {
        initialState.postValue(NetworkState.SUCCESS)
        networkState.postValue(NetworkState.SUCCESS)
        retry = null
        onLoadInitialSuccess(params, callback, data)
        val submitData = data ?: emptyList()
        val previousPage = startedPage - 1
        val nextPage = startedPage + 1
        when {
            isLoadAfter && isLoadBefore -> callback.onResult(submitData, previousPage, nextPage)
            isLoadAfter -> callback.onResult(submitData, null, nextPage)
            isLoadBefore -> callback.onResult(submitData, previousPage, null)
        }
    }

    open fun onLoadInitialSuccess(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, V>,
        data: List<V>?
    ) {
    }

    /**
     * Used when [loadInitial] error
     */
    fun handleLoadInitialError(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, V>,
        throwable: Throwable
    ) {
        val errorMsg = throwable.message ?: ApiConstants.DEF_ERROR_MSG
        val error = NetworkState.error(errorMsg)
        networkState.postValue(error)
        retry = { loadInitial(params, callback) }
        onLoadInitialError(throwable)
    }

    open fun onLoadInitialError(throwable: Throwable) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, V>) {
        if (isLoadAfter.not()) return
        networkState.postValue(NetworkState.RUNNING)
        loadAfterFromSource(params, callback)
    }

    abstract fun loadAfterFromSource(params: LoadParams<Int>, callback: LoadCallback<Int, V>)

    /**
     * Handle when [loadAfterFromSource] success
     */
    fun handleLoadAfterSuccess(
        params: LoadParams<Int>, callback: LoadCallback<Int, V>, data: List<V>?, totalPage: Int?
    ) {
        networkState.postValue(NetworkState.SUCCESS)
        retry = null
        onLoadAfterSuccess(params, callback, data)
        val submitData = data ?: emptyList()
        val nextKey = if (params.key == totalPage) null else (params.key + 1)
        callback.onResult(submitData, nextKey)
    }

    open fun onLoadAfterSuccess(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, V>,
        data: List<V>?
    ) {
    }

    /**
     * Handle when [loadAfter] success
     */
    fun handleLoadAfterError(
        params: LoadParams<Int>, callback: LoadCallback<Int, V>, throwable: Throwable
    ) {
        val errorMsg = throwable.message ?: ApiConstants.DEF_ERROR_MSG
        val error = NetworkState.error(errorMsg)
        networkState.postValue(error)
        retry = { loadAfter(params, callback) }
        onLoadAfterError(throwable)
    }

    open fun onLoadAfterError(throwable: Throwable) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, V>) {
        if (isLoadBefore.not()) return
        networkState.postValue(NetworkState.RUNNING)
        loadBeforeFromSource(params, callback)
    }

    abstract fun loadBeforeFromSource(params: LoadParams<Int>, callback: LoadCallback<Int, V>)

    /**
     * Handle when [loadBefore] success
     */
    fun handleLoadBeforeSuccess(
        params: LoadParams<Int>, callback: LoadCallback<Int, V>, data: List<V>?, startedPage: Int?
    ) {
        networkState.postValue(NetworkState.SUCCESS)
        retry = null
        onLoadBeforeSuccess(params, callback, data)
        val submitData = data ?: emptyList()
        val nextKey = if (params.key == startedPage) null else (params.key - 1)
        callback.onResult(submitData, nextKey)
    }

    open fun onLoadBeforeSuccess(
        params: LoadParams<Int>, callback: LoadCallback<Int, V>, data: List<V>?
    ) {
    }

    /**
     * Handle when [loadBefore] error
     */
    fun handleLoadBeforeError(
        params: LoadParams<Int>, callback: LoadCallback<Int, V>, throwable: Throwable
    ) {
        val errorMsg = throwable.message ?: ApiConstants.DEF_ERROR_MSG
        val error = NetworkState.error(errorMsg)
        networkState.postValue(error)
        retry = { loadBefore(params, callback) }
        onLoadBeforeError(throwable)
    }

    open fun onLoadBeforeError(throwable: Throwable) {
    }
}
