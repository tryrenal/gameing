package com.redveloper.creator.core.domain.usecase

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.creator.core.domain.model.Creator
import com.redveloper.creator.core.domain.repository.RepositoryCreatorImpl
import kotlinx.coroutines.flow.Flow

class CreatorInteractor (
    private val repositoryCreatorImpl: RepositoryCreatorImpl
) : CreatorUseCase {
    override fun getAllCreator(): Flow<Resource<PagingData<Creator>>> = repositoryCreatorImpl.getAllCreator()
    override fun getAllCreatorPager(): Flow<PagingData<Creator>> = repositoryCreatorImpl.getAllCreaorPager()
}