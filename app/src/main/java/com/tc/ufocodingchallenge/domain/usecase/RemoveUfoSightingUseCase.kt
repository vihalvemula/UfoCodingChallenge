package com.tc.ufocodingchallenge.domain.usecase

import com.tc.ufocodingchallenge.domain.repository.UfoRepository
import java.util.UUID
import javax.inject.Inject

class RemoveUfoSightingUseCase @Inject constructor(private val repository: UfoRepository) {
    operator fun invoke(id: UUID) {
        repository.removeSighting(id)
    }
}