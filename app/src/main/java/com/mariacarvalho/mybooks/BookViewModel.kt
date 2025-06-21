package com.mariacarvalho.mybooks

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    private val _books = mutableStateListOf<Book>()
    val books: List<Book> get() = _books

    fun addBook(title: String, author: String) {
        val newBook = Book(
            id = _books.size + 1,
            title = title,
            author = author
        )
        _books.add(newBook)
    }

    fun readStatus(book: Book) {
        val index = _books.indexOfFirst { it.id == book.id }
        if (index >= 0) {
            _books[index] = _books[index].copy(isRead = !_books[index].isRead)
        }
    }

    fun deleteBook(book: Book) {
        _books.remove(book)
    }
}