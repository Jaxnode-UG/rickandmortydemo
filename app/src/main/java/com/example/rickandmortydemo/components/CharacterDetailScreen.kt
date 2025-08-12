package com.example.rickandmortydemo.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmortydemo.interfaces.CharacterDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(state: CharacterDetailUiState, onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Character Detail") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
        })
    }) { paddings ->
        when (state) {
            CharacterDetailUiState.Loading -> LoadingBox(Modifier.padding(paddings))
            is CharacterDetailUiState.Error -> ErrorBox(state.message, onRetry = onBack, modifier = Modifier.padding(paddings))
            is CharacterDetailUiState.Data -> DetailBody(ch = state.character, modifier = Modifier.padding(paddings))
        }
    }
}
