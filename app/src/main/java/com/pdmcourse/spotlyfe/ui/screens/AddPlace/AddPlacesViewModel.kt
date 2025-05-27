package com.pdmcourse.spotlyfe.ui.screens.AddPlace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse.spotlyfe.SpotLyfeApplication
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepository
import com.pdmcourse.spotlyfe.data.model.Place
import kotlinx.coroutines.launch

class AddPlacesViewModel(private val placeRepository: PlaceRepository) : ViewModel() {

    fun addPlace(place: Place) {
        viewModelScope.launch {
            placeRepository.addPlace(place)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as SpotLyfeApplication
                AddPlacesViewModel(
                    aplication.appProvider.providePlaceRepository()
                )
            }
        }
    }
}