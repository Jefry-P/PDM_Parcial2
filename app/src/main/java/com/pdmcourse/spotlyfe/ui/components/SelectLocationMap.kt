package com.pdmcourse.spotlyfe.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun SelectioncationMap(
    initialPosition: LatLng = LatLng(13.679024407659101, -89.23578718993471),
    onLocationChanged: (LatLng) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 16f)
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }

    val markerState = rememberMarkerState(position = initialPosition)

    Box(
        modifier = Modifier
            .height(400.dp)
    ) {
        GoogleMap(
            modifier = Modifier.height(400.dp),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            onMapClick = { newLating ->
                markerState.position = newLating
                onLocationChanged(newLating)
                Log.d("SelectioncationMap", "New Location selected: $newLating")
            }
        ) {

            Marker(state = markerState)
        }
    }
}

