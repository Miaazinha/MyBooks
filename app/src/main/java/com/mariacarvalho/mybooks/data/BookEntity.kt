package com.mariacarvalho.mybooks.data


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, //Cria um id automático
    val title: String,
    val author: String,
    val isRead: Boolean = false
)