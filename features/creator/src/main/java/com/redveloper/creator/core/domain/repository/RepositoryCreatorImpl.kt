package com.redveloper.creator.core.domain.repository

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.coroutines.flow.Flow

interface RepositoryCreatorImpl  {
    fun getAllCreaorPager() : Flow<PagingData<Creator>>
}