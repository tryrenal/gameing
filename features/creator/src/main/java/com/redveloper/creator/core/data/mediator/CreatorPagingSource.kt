package com.redveloper.creator.core.data.mediator

import androidx.paging.PagingSource
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.creator.core.utils.CreatorMapper
import java.io.IOException

class CreatorPagingSource (private val remoteDataSource: RemoteDataSource) : PagingSource<Int, CreatorEntity>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CreatorEntity> {
        val page = params.key ?: 1
        return try {
            val response = remoteDataSource.getAllCreator(page)
            val data = CreatorMapper.responseToEntity(response)
            LoadResult.Page(
                data, prevKey = if (page == 1) null  else page - 1,
                nextKey = if (data.isEmpty()) null  else page + 1
            )
        } catch (e: IOException){
            return LoadResult.Error(e)
        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}