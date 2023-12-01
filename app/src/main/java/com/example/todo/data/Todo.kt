package com.example.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    val todo: String,
    val time: String,
    val isComplete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Long = -1L,
)
