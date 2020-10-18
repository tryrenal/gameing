package com.redveloper.core.data.source.remote.network

import com.redveloper.core.data.source.remote.response.game.RootGameResponse
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    suspend fun getAllGames(): RootGameResponse
}