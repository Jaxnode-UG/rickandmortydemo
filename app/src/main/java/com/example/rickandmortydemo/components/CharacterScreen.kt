package com.example.rickandmortydemo.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmortydemo.dto.CharactersUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    state: CharactersUiState,
    onRetry: () -> Unit,
    onOpen: (Int) -> Unit,
    onLoadMore: () -> Unit
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Rick & Morty") }) }) { paddings ->
        when {
            state.loading && state.characters.isEmpty() -> LoadingBox(Modifier.padding(paddings))
            state.error != null && state.characters.isEmpty() -> ErrorBox(message = state.error, onRetry = onRetry, modifier = Modifier.padding(paddings))
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(paddings)) {
                    items(state.characters) { ch ->
                        CharacterRow(ch = ch, onClick = { onOpen(ch.id) })
                    }
                    item {
                        if (state.loading) LoadingMore()
                        else if (state.hasNext) LoadMoreButton(onClick = onLoadMore)
                    }
                }
            }
        }
    }
}