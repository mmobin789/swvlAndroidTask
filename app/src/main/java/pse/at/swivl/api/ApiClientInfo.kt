package pse.at.swivl.api

import com.google.gson.Gson

interface ApiClientInfo {
    val apiClient: NetworkClient.API
        get() = NetworkClient.getApiClient()

    val gson: Gson
        get() = Gson()
}