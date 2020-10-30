package com.redveloper.creator.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.redveloper.core.data.NetworkBoundResource
import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.remote.ApiResponse
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.response.creator.CreatorResponse
import com.redveloper.core.utils.AppExecutors
import com.redveloper.core.vo.Resource
import com.redveloper.creator.core.domain.model.Creator
import com.redveloper.creator.core.domain.repository.RepositoryCreatorImpl
import com.redveloper.creator.core.utils.CreatorMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryCreator (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : RepositoryCreatorImpl  {
    override fun getAllCreator(): Flow<Resource<PagingData<Creator>>> {
        return object : NetworkBoundResource<PagingData<Creator>, List<CreatorResponse>>(){
            override fun loadFromDB(): Flow<PagingData<Creator>> {
                return Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = true,
                        maxSize = 200
                    )
                ){
                    localDataSource.getAllCreator()
                }.flow
                    .map {
                        CreatorMapper.entityToDomain(it)
                    }
            }

            override fun shouldFetch(data: PagingData<Creator>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<CreatorResponse>>> {
                return remoteDataSource.getAllCreator(page = 1)
            }

            override suspend fun saveCallResult(data: List<CreatorResponse>) {
                val creatorEntity = CreatorMapper.responseToEntity(data)
                localDataSource.insertCreator(creatorEntity)
            }
        }.asFlow()
    }
}