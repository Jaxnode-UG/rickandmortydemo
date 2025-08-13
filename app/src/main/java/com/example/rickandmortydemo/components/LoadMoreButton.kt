package com.example.rickandmortydemo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LoadMoreButton(onClick: () -> Unit) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = onClick) { Text("Load more")
        }
    }
}

@Preview(widthDp = 100, showBackground = false)
@Composable
fun LoadMoreButtonPreview() {
    LoadMoreButton(onClick = {})
}