package com.redveloper.home.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.redveloper.core.data.NetworkBoundResource
import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.remote.ApiResponse
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.core.data.source.remote.response.game.GameResponse
import com.redveloper.core.utils.AppExecutors
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import com.redveloper.home.core.utils.GameMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryHome(
    private val apiService: ApiService,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : RepositoryHomeImpl {

    override fun getAllGamePager(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
            pagingSourceFactory = {GamePagingSource(apiService = apiService)}
        ).flow
            .map { 
                GameMapper.entityToDomainPaging(it)
            }
    }

    override fun getAllGames(): Flow<Resource<PagingData<Game>>> {
        return object : NetworkBoundResource<PagingData<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<PagingData<Game>> {
                return Pager(
                    PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = true,
                        maxSize = 200
                    )
                ) {
                    localDataSource.getAllGame()
                }.flow
                    .map {
                        GameMapper.entityToDomainPaging(it)
                    }
            }

            override fun shouldFetch(data: PagingData<Game>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> {
                return remoteDataSource.getAllGames()
            }

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameEntity = GameMapper.responseToEntityList(data)
                localDataSource.insertGame(gameEntity)
            }
        }.asFlow()
    }

    override fun getGameById(id: Int): Flow<Resource<Game>> {
        return object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGameById(id).map { GameMapper.entityToDomain(it) }
            }

            override fun shouldFetch(data: Game?): Boolean {
                return data?.desc == null
            }

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> {
                return remoteDataSource.getDetailGame(id)
            }

            override suspend fun saveCallResult(data: GameResponse) {
                val gameEntity = GameMapper.responseToEntity(data)
                appExecutors.diskIO().execute { localDataSource.updateGame(gameEntity) }
            }
        }.asFlow()
    }

    override fun getFavoriteGame(): Flow<PagingData<Game>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 200
            )
        ) {
            localDataSource.getFavoriteGame()
        }.flow
            .map {
                GameMapper.entityToDomainPaging(it)
            }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = GameMapper.domainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoritGame(gameEntity, state) }
    }
}