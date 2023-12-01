package com.example.todo

import com.example.todo.data.Todo
import com.example.todo.data.TodoDao
import kotlinx.coroutines.Dispatchers

class TodoRepository(private val todoDao: TodoDao) {

    fun getAllTodos() = todoDao.selectAll()
    suspend fun insertTodo(todo: Todo) = Dispatchers.IO.apply { todoDao.insert(todo) }
    fun deleteAllTodo() = todoDao.deleteAllTodo()
    suspend fun deleteTodoId(todo: Todo) = Dispatchers.IO.apply { todoDao.delete(todo.id) }
    suspend fun updateTodos(isComplete: Boolean, id: Long) = todoDao.updateTodo(isComplete, id)
}
