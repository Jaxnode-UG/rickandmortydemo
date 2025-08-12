package com.example.rickandmortydemo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortydemo.dto.Character

@Composable
fun DetailBody(ch: Character, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        AsyncImage(
            model = ch.image,
            contentDescription = ch.name,
            modifier = Modifier.fillMaxWidth().height(260.dp),
            contentScale = ContentScale.Crop
        )
        Text(ch.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Status: ${ch.status}")
        Text("Species: ${ch.species}")
        Text("Gender: ${ch.gender}")
        ch.origin?.let { Text("Origin: ${it.name}") }
        ch.location?.let { Text("Location: ${it.name}") }
    }
}