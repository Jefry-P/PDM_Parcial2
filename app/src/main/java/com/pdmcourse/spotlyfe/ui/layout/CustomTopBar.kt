package com.pdmcourse.spotlyfe.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
  title: String = "SpotLyfe",
  showBackButton: Boolean = false,
  onBackPressed:  () -> Unit = {},
) {
  TopAppBar(
    title = { Text(text = title) },
    navigationIcon = {
      if (showBackButton) {
        IconButton(onClick = onBackPressed) {
          Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "Volver"
          )
        }
      } else {
        IconButton(onClick = {  }) {
          Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Menú"
          )
        }
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, titleContentColor = MaterialTheme.colorScheme.secondary),
    actions = {
    },
  )
}