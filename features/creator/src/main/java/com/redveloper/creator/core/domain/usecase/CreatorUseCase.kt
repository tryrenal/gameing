package com.redveloper.creator.core.domain.usecase

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.coroutines.flow.Flow

interface CreatorUseCase {
    fun getAllCreatorPager() : Flow<PagingData<Creator>>
}