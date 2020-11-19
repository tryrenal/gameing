package com.redveloper.home.core.data.mediator

import androidx.paging.PagingSource
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.home.core.utils.GameMapper
import java.io.IOException

class GamePagingSource (private val apiService: ApiService) : PagingSource<Int, GameEntity>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameEntity> {
        val page = params.key ?: 1
        return  try {
            val response = apiService.getAllGames(page).results
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