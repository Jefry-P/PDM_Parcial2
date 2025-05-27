package com.pdmcourse.spotlyfe.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao{
    @Query("SELECT * FROM places")
    fun getPlaces(): Flow<List<PlaceEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlace(place: PlaceEntity)
}
