package com.example.rickandmortydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortydemo.components.CharacterDetailScreen

import com.example.rickandmortydemo.components.CharactersScreen
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












