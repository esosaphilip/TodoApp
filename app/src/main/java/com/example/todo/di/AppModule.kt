package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.TodoRepository
import com.example.todo.data.TodoDao
import com.example.todo.data.TodoDatabase
import com.example.todo.detail.DetailViewModel
import com.example.todo.home.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TodoViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val databaseModule = module {
    single { provideTodoDatabase(get()) }
    single { provideTodoDao(get()) }
    single { TodoRepository(get()) }
}

fun provideTodoDatabase(application: Application): TodoDatabase {
    return Room.databaseBuilder(
        application,
        TodoDatabase::class.java,
        "todo_database",
    ).build()
}

fun provideTodoDao(db: TodoDatabase): TodoDao {
    return db.getTodoDao()
}
