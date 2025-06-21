package com.mariacarvalho.mybooks

data class Book (
    val id: Int,
    val title: String,
    val author: String,
    val isRead: Boolean = false
)

