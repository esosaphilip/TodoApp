package com.example.todo.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.TodoRepository
import com.example.todo.data.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: TodoRepository,
) : ViewModel() {
    private val todoText = MutableStateFlow("")
    private val todoTime = MutableStateFlow("")
    private val selecteId = MutableStateFlow(-1L)

    private val _state = MutableStateFlow(DetailViewState())
    val state: StateFlow<DetailViewState> = _state

    init {
        viewModelScope.launch {
            combine(todoText, todoTime, selecteId) {
                    text, time, id ->
                DetailViewState(text, time, id)
            }.collect {
                _state.value = it
            }
        }
    }

    init {
        viewModelScope.launch {
            repository.getAllTodos().collect { todo ->
                todo.find {
                    it.id == selecteId.value
                }.also {
                    if (it != null) {
                        selecteId.value = it.id
                    }
                    if (selecteId.value != -1L) {
                        if (it != null) {
                            todoText.value = it.todo
                        }
                        if (it != null) {
                            todoTime.value = it.time
                        }
                    }
                }
            }
        }
    }

    fun onTextChange(newText: String) {
        todoText.value = newText
    }
    fun onTimeChange(newText: String) {
        todoText.value = newText
    }
    fun insert(todo: Todo) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }
}
data class DetailViewState(
    val todo: String = "",
    val time: String = "",
    val selectedId: Long = -1L,
)
