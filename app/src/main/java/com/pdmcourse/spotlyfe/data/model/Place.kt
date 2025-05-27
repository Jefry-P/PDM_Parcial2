package com.pdmcourse.spotlyfe.data.model

import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity

data class Place(
  val id: Int,
  val name: String,
  val description: String,
  val remark: String,
  val latitude: Double,
  val longitude: Double,
)

fun Place.toEntity(): PlaceEntity {
  return PlaceEntity(
    id = id,
    name = name,
    description = description,
    remark = remark,
    latitude = latitude,
    longitude = longitude
  )
}
