package com.mariacarvalho.mybooks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.CardDefaults



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookList(viewModel: BookViewModel = viewModel()) {
    val books = viewModel.books

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MyBooks",
                        fontSize = 28.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF6200EE)
                )
            )
        },
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF3E5F5))
                    .padding(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.bookshelf),
                    contentDescription = "Estante de livros",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(bottom = 12.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Lista de Livros",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF4A148C)
                )
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(books) { book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(book.title, style = MaterialTheme.typography.titleMedium, color = Color(0xFF512DA8))
                                    Text(book.author, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF7E57C2))
                                    Text(if (book.isRead) "Lido" else "Por Ler", color = Color(0xFF311B92))
                                }
                                Row {
                                    IconButton(onClick = { viewModel.readStatus(book) }) {
                                        Icon(Icons.Default.Check, contentDescription = "Marcar como Lido", tint = Color(0xFF2E7D32))
                                    }
                                    IconButton(onClick = { viewModel.deleteBook(book) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Apagar Livro", tint = Color(0xFFC62828))
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
    )
}