package com.redveloper.core.data.source.remote.network

import com.redveloper.core.data.source.remote.response.creator.RootCreatorResponse
import com.redveloper.core.data.source.remote.response.game.GameResponse
import com.redveloper.core.data.source.remote.response.game.RootGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getAllGames(
        @Query("page") page: Int
    ): RootGameResponse

    @GET("games/{id}")
    suspend fun getDetailGames(
        @Path("id") id: Int
    ) : GameResponse

    @GET("creators")
    suspend fun getAllCreator(
        @Query("page") page: Int
    ) : RootCreatorResponse
}