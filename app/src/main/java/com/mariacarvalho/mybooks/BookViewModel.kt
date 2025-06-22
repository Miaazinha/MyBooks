package com.mariacarvalho.mybooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariacarvalho.mybooks.data.BookEntity
import com.mariacarvalho.mybooks.data.BookRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableStateFlow<List<BookEntity>>(emptyList())
    val books: StateFlow<List<BookEntity>> = _books.asStateFlow()

    init {
        loadBooks()
    }

    fun addBook(title: String, author: String) {
        viewModelScope.launch {
            val book = BookEntity(title = title, author = author)
            repository.insertBook(book)
            loadBooks()
        }
    }
    private fun loadBooks(){
        viewModelScope.launch {
            repository.getAllBooks().collect { bookList ->
                _books.value = bookList
            }
        }
    }

    fun readStatus(book: BookEntity) {
        viewModelScope.launch {
            val updated = book.copy(isRead = !book.isRead)
            repository.updateBook(updated)
            loadBooks()
        }
    }

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
            loadBooks()
        }
    }
}