package com.pdmcourse.spotlyfe.data.database.repository

import com.pdmcourse.spotlyfe.data.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    fun getPlaces(): Flow<List<Place>>
    suspend fun addPlace(place: Place)
}