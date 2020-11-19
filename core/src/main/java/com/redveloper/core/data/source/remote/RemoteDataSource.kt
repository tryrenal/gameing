package com.redveloper.core.data.source.remote

import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.core.data.source.remote.response.creator.CreatorResponse
import com.redveloper.core.data.source.remote.response.game.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGames(page: Int): List<GameResponse> {
        return apiService.getAllGames(page).results
    }

    fun getDetailGame(id: Int): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGames(id)
                emit(ApiResponse.Success(response))
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllCreator(page: Int): List<CreatorResponse> {
        return apiService.getAllCreator(page).results
    }
}