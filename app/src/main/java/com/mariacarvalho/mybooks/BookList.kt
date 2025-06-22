package com.mariacarvalho.mybooks

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookList(viewModel: BookViewModel = viewModel(), navController: NavController){
    val books by viewModel.books.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MyBooks",
                        fontSize = 24.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF80CBC4))

            )
        },
        content = { paddingValues ->

            Column (
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF3E5F5))
                    .padding(16.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.bookshelf),
                    contentDescription = "Estante de Livros",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(bottom = 12.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Lista de Livros",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF00796B)
                )
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(modifier = Modifier.weight(1f)){
                    items(books) { book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ){
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Column {
                                    Text(book.title, style = MaterialTheme.typography.titleMedium, color = Color(0xFF00796B))
                                    Text(book.author, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF009688))
                                    Text(if (book.isRead) "Lido" else "Por Ler", color = Color(0xFF004D40))
                                    if (book.opinion.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text("Opinião: ${book.opinion}", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
                                    }
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