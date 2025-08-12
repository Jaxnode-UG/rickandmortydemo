package com.example.rickandmortydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.rickandmortydemo.components.CharacterDetailScreen
import com.example.rickandmortydemo.components.CharacterRow
import com.example.rickandmortydemo.components.CharactersScreen
import com.example.rickandmortydemo.components.DetailBody
import com.example.rickandmortydemo.components.ErrorBox
import com.example.rickandmortydemo.components.LoadMoreButton
import com.example.rickandmortydemo.components.LoadingBox
import com.example.rickandmortydemo.components.LoadingMore
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

import com.example.rickandmortydemo.dto.CharactersResponse
import com.example.rickandmortydemo.dto.Character
import com.example.rickandmortydemo.dto.CharactersUiState
import com.example.rickandmortydemo.interfaces.CharacterDetailUiState
import com.example.rickandmortydemo.interfaces.RickAndMortyApi

import com.example.rickandmortydemo.ui.theme.RickandmortydemoTheme
import com.example.rickandmortydemo.viewmodels.CharacterDetailViewModel
import com.example.rickandmortydemo.viewmodels.CharactersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandmortydemoTheme {
                RickAndMortyApp()
            }
        }
    }
}

@Composable
fun RickAndMortyApp() {
    MaterialTheme {
        val nav = rememberNavController()
        NavHost(navController = nav, startDestination = "list") {
            composable("list") { CharactersRoute(onOpen = { id -> nav.navigate("detail/$id") }) }
            composable(
                route = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStack ->
                val id = backStack.arguments?.getInt("id") ?: 0
                CharacterDetailRoute(id = id, onBack = { nav.popBackStack() })
            }
        }
    }
}


// -----------------------------------------
// Routes (VM wiring) + Screens
// -----------------------------------------
@Composable
fun CharactersRoute(onOpen: (Int) -> Unit) {
    val vm = remember { CharactersViewModel() }
    val state by vm.state.collectAsStateWithLifecycle()
    CharactersScreen(state = state, onRetry = { vm.refresh() }, onOpen = onOpen, onLoadMore = { vm.nextPage() })
}

@Composable
fun CharacterDetailRoute(id: Int, onBack: () -> Unit) {
    val vm = remember(id) { CharacterDetailViewModel(id) }
    val state by vm.state.collectAsStateWithLifecycle()
    CharacterDetailScreen(state = state, onBack = onBack)
}












