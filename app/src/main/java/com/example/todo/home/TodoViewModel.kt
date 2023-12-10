package com.example.todo.home // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.TodoRepository
import com.example.todo.data.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> = _homeState

    private val todoList = repository.getAllTodos()
    private val selected = MutableStateFlow(_homeState.value.selected)

    init {
        viewModelScope.launch {
            combine(todoList, selected) { todos: List<Todo>, selected: Boolean ->
                HomeViewState(todos, selected)
            }.collect {
                _homeState.value = it
            }
        }
    }

    fun updateTodos(selected: Boolean, id: Long) = viewModelScope.launch {
        repository.updateTodos(selected, id)
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        repository.deleteTodoId(todo)
    }
}

data class HomeViewState(
    val todoList: List<Todo> = emptyList(),
    val selected: Boolean = false,
)
