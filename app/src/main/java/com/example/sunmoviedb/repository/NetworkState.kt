package com.example.sunmoviedb.repository

/**
 * Created on 4/3/19 by Sang
 * Description:
 **/
enum class NetworkStatus {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(status: NetworkStatus, msg: String? = null) {

    companion object {
        val SUCCESS = NetworkState(NetworkStatus.SUCCESS)
        val RUNNING = NetworkState(NetworkStatus.RUNNING)

        fun error(msg: String?): NetworkState = NetworkState(NetworkStatus.FAILED, msg)
    }
}
