package com.redveloper.home.core.data

import com.redveloper.core.data.NetworkBoundResource
import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.remote.ApiResponse
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.response.game.GameResponse
import com.redveloper.core.utils.AppExecutors
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import com.redveloper.home.core.utils.GameMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryHome (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : RepositoryHomeImpl {

    override fun getAllGames(): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameResponse>>(){
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame()
                    .map { GameMapper.entityToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> {
                return remoteDataSource.getAllGames()
            }

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameEntity = GameMapper.responseToEntity(data)
                localDataSource.insertGame(gameEntity)
            }
        }.asFlow()
    }
}