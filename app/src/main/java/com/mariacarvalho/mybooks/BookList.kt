package com.mariacarvalho.mybooks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BookList(viewModel: BookViewModel = viewModel()){
    val books = viewModel.books

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lista de Livros", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(books) { book ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(book.title, style = MaterialTheme.typography.titleMedium)
                            Text(book.author, style = MaterialTheme.typography.bodyMedium)
                            Text(if (book.isRead) "✅ Lido" else "📖 Por ler")
                        }

                        Row {
                            IconButton(onClick = { viewModel.readStatus(book) }) {
                                Icon(Icons.Default.Check, contentDescription = "Marcar como lido")
                            }
                            IconButton(onClick = { viewModel.deleteBook(book) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Apagar livro")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        AddBookForm(viewModel)
    }
}