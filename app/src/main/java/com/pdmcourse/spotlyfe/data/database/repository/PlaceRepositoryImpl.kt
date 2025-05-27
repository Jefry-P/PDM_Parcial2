package com.pdmcourse.spotlyfe.data.database.repository

import androidx.room.Transaction
import com.pdmcourse.spotlyfe.data.database.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.toDomain
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepositoryImpl(private val placeDao: PlaceDao) : PlaceRepository {
    override fun getPlaces(): Flow<List<Place>> {
        return placeDao.getPlaces().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    @Transaction
    override suspend fun addPlace(place: Place) {
        placeDao.addPlace(place.toEntity())
    }
}