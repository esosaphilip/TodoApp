package com.example.todo

import android.app.Application
import com.example.todo.di.databaseModule
import com.example.todo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TodoApplication)
            androidFileProperties()
            // Load modules
            modules(
                viewModelModule,
                databaseModule,
            )
        }
    }
}
