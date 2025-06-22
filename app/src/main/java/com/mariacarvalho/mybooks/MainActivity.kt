package com.mariacarvalho.mybooks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mariacarvalho.mybooks.ui.theme.MyBooksTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.room.util.TableInfo
import com.mariacarvalho.mybooks.data.BookDatabase
import com.mariacarvalho.mybooks.data.BookRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.mariacarvalho.mybooks.data.BookEntity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBooksTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "welcome") {
                    composable("welcome") {
                        WelcomeScreen(onStartClick = {
                            navController.navigate("books")
                        })
                    }
                    composable("books") {
                        val context = LocalContext.current
                        val db = remember { BookDatabase.getDatabase(context) }
                        val repository = remember { BookRepository(db.bookDao()) }
                        val viewModel: BookViewModel = viewModel(factory = BookViewModelFactory(repository))

                        BookListScreen(viewModel = viewModel, navController = navController)
                    }

                }

            }
        }
    }
}

@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo ao MyBooks!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Icon(
            imageVector = Icons.Default.MenuBook,
            contentDescription = "Ícone de livros",
            tint = Color(0xFF26A69A),
            modifier = Modifier.size(96.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button (onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26A69A))
        ) {
            Text("Entrar")
        }
    }
}

@Composable
fun BookListScreen(viewModel: BookViewModel = viewModel(), navController: NavController) {
    BookList(viewModel, navController)
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyBooksTheme {
        Greeting("Android")
    }
}