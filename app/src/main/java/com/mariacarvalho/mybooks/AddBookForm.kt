package com.mariacarvalho.mybooks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun AddBookForm(viewModel: BookViewModel){
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    OutlinedTextField(
        value = title,
        onValueChange = { title = it },
        label = { Text("Título do livro") },
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
    )
    OutlinedTextField(
        value = author,
        onValueChange = { author = it },
        label = { Text("Autor") },
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
    )
    Button (
        onClick = {
            if (title.isNotBlank() && author.isNotBlank()) {
                viewModel.addBook(title, author)
                title = ""
                author = ""
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4DB6AC))
    ) {
        Text("Adicionar Livro")
    }
}