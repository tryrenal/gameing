package com.redveloper.creator.core.domain.repository

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.coroutines.flow.Flow

interface RepositoryCreatorImpl  {
    fun getAllCreator() : Flow<Resource<PagingData<Creator>>>
    fun getAllCreaorPager() : Flow<PagingData<Creator>>
}