package com.pdmcourse.spotlyfe.ui.screens.AddPlace

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.components.SelectioncationMap
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar

@Composable
fun AddPlaceScreen(navController: NavController, viewModel: AddPlacesViewModel = viewModel(factory = AddPlacesViewModel .Factory)) {

    val context = LocalContext.current
    val newPlace = remember { mutableStateOf(Place(
        id = 0,
        name = "",
        description = "",
        remark = "",
        latitude = 0.0,
        longitude = 0.0
    )) }
    Scaffold(
        topBar = { CustomTopBar(onBackPressed = { navController.popBackStack() }, title = "Agregar Lugar",
            showBackButton = true, ) },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = newPlace.value.name,
                onValueChange = {
                    newPlace.value = newPlace.value.copy(name = it)
                    newPlace.value = newPlace.value.copy(remark = it)
                },
                label = { Text("Nombre") },
                placeholder = { Text("Nombre") },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            TextField(
                value = newPlace.value.description,
                onValueChange = {
                    newPlace.value = newPlace.value.copy(description = it)
                },
                label = { Text("Description") },
                placeholder = { Text("Description") },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )



            SelectioncationMap(
                onLocationChanged = {
                    newPlace.value = newPlace.value.copy(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                }
            )

            Button(
                onClick = {
                    viewModel.addPlace(newPlace.value)
                    Toast.makeText(
                        context,
                        "${newPlace.value.name} guardado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                },
                modifier = Modifier.padding(top = 16.dp).width(200.dp)
            ) {
                Text("Save")
            }
        }
    }
}