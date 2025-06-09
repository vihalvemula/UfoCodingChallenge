package com.tc.ufocodingchallenge.data.repoImpl

import com.tc.ufocodingchallenge.data.model.UfoSighting
import com.tc.ufocodingchallenge.domain.repository.UfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class UfoRepositoryImpl @Inject constructor() : UfoRepository {

    private val sightings = MutableStateFlow<List<UfoSighting>>(emptyList())

    override fun getSightings(): Flow<List<UfoSighting>> = sightings

    override fun addSighting(sighting: UfoSighting) {
        sightings.value += sighting
    }

    override fun removeSighting(id: UUID) {
        sightings.value = sightings.value.filterNot { it.id == id }
    }
}