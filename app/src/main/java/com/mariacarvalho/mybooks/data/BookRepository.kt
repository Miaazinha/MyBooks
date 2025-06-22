package com.mariacarvalho.mybooks.data

import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookdao: BookDao){

    fun getAllBooks(): Flow<List<BookEntity>> = bookdao.getAllBooks()

    suspend fun insertBook(book: BookEntity){
        bookdao.insertBook(book)
    }
    suspend fun deleteBook(book: BookEntity){
        bookdao.deleteBook(book)
    }
    suspend fun updateBook(book: BookEntity){
        bookdao.updateBook(book)
    }

}