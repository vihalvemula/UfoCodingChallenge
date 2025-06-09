package com.tc.ufocodingchallenge.domain.usecase

import com.tc.ufocodingchallenge.data.model.UfoSighting
import com.tc.ufocodingchallenge.domain.repository.UfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUfoSightingsUseCase @Inject constructor(private val repository: UfoRepository) {
    operator fun invoke(): Flow<List<UfoSighting>> = repository.getSightings()
}