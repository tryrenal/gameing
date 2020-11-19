package com.redveloper.creator.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.redveloper.core.data.NetworkBoundResource
import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.remote.ApiResponse
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.core.data.source.remote.response.creator.CreatorResponse
import com.redveloper.core.utils.AppExecutors
import com.redveloper.core.vo.Resource
import com.redveloper.creator.core.domain.model.Creator
import com.redveloper.creator.core.domain.repository.RepositoryCreatorImpl
import com.redveloper.creator.core.utils.CreatorMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryCreator (
    private val apiService: ApiService,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : RepositoryCreatorImpl  {

    override fun getAllCreaorPager(): Flow<PagingData<Creator>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
            pagingSourceFactory = {CreatorPagingSource(apiService = apiService)}
        ).flow
            .map { CreatorMapper.entityToDomain(it) }
    }
}