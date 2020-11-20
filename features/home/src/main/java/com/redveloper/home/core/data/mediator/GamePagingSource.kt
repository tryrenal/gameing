package com.redveloper.home.core.data.mediator

import androidx.paging.PagingSource
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.home.core.utils.GameMapper
import java.io.IOException

class GamePagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val search: String?
) : PagingSource<Int, GameEntity>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameEntity> {
        val page = params.key ?: 1
        return  try {
            val response = remoteDataSource.getAllGames(page, search)
            val data = GameMapper.responseToEntityList(response)
            LoadResult.Page(
                data, prevKey = if (page == 1) null  else page - 1,
                nextKey = if (data.isEmpty()) null  else page + 1
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}