package com.tc.ufocodingchallenge.domain.usecase


import com.tc.ufocodingchallenge.data.model.UfoSighting
import com.tc.ufocodingchallenge.domain.repository.UfoRepository
import javax.inject.Inject

class AddUfoSightingUseCase @Inject constructor(private val repository: UfoRepository) {
    operator fun invoke(sighting: UfoSighting) {
        repository.addSighting(sighting)
    }
}