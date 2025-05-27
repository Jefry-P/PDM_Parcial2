package com.pdmcourse.spotlyfe.data

import android.content.Context
import com.pdmcourse.spotlyfe.data.database.AppDatabase
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepository
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepositoryImpl


class AppProvider(context: Context) {

  private val appDatabase = AppDatabase.getDatabase(context)
  private val placeDao = appDatabase.placeDao()
  private val placeRepository: PlaceRepository = PlaceRepositoryImpl(placeDao)

  fun providePlaceRepository() : PlaceRepository {
    return placeRepository;
  }
}