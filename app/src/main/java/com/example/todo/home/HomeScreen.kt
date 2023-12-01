package com.example.todo.home

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todo.data.Todo
import org.koin.androidx.compose.koinViewModel

// ktlint-disable package-name

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(onNavigate: (Todo?) -> Unit) {
    val viewModel = koinViewModel<TodoViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigate(null) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
        LazyColumn() {
            items(state.todoList) { todo ->
                TodoItem(
                    todo = todo,
                    onChecked = { viewModel.updateTodos(it, todo.id) },
                    onDelete = { viewModel.delete(it) },
                    onNavigation = { onNavigate(it) },
                )
            }
        }
    }
}
