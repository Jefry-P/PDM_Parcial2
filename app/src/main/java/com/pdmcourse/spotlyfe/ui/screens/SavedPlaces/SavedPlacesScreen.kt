package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import com.pdmcourse.spotlyfe.ui.navigation.AddPlacesScreenNavigation
import com.pdmcourse.spotlyfe.ui.screens.AddPlace.AddPlacesViewModel

@Composable
fun SavedPlacesScreen(navController: NavController, viewModel: SavedPlacesViewModel = viewModel(factory = SavedPlacesViewModel .Factory)) {

  val places by viewModel.places.collectAsState()
  val loading by viewModel.loading.collectAsState()

  val UCA = Place(
      name = "Centro Monseñor Romero",
      remark = "Marker in Centro Monseñor Romero",
      latitude = 13.679024407659101,
      longitude = -89.23578718993471,
      id = 0,
      description = "",
  )

  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(LatLng(UCA.latitude, UCA.longitude), 16f)
  }

  var uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
  }
  var properties by remember {
    mutableStateOf(MapProperties(mapType = MapType.HYBRID))
  }

  Scaffold(
    topBar = { CustomTopBar() },
    floatingActionButton = { CustomFloatingButton(onClick = {navController.navigate(
      AddPlacesScreenNavigation
    )})}
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

      when {
        loading -> {
          CircularProgressIndicator(
            modifier = Modifier.align(Alignment.CenterHorizontally)
          )
        }
        places.isEmpty() -> {
          Text(
            text = "Empty places",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
              .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
          )
        }
        else -> {

          GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
          ) {

            places.forEach { place ->
              Marker(
                state = MarkerState(position = LatLng(place.latitude, place.longitude)),
                title = place.name,
                snippet = place.remark
              )
            }
          }
        }
      }


    }
  }
}