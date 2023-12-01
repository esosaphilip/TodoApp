package com.example.todo.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    selectedId: Long,
    onNavigate: () -> Unit,
) {
    val viewModel = koinViewModel<DetailViewModel>()

    val state by viewModel.state.collectAsState()

    DetailScreenComponent(
        todoText = state.todo,
        onTodoTextChange = { viewModel.onTextChange(it) },
        timeText = state.time,
        onTimeTextChange = { viewModel.onTimeChange(it) },
        onNavigate = { onNavigate() },
        onSaveTodo = { viewModel.insert(it) },
        selectedId = state.selectedId,
    )
}
