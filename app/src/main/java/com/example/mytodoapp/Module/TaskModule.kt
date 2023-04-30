package com.example.mytodoapp.Module

import android.app.Application
import androidx.room.Room
import com.example.mytodoapp.TodoDatabase.Repository.TodoRepository
import com.example.mytodoapp.TodoDatabase.Repository.TodoRepositoryImp
import com.example.mytodoapp.TodoDatabase.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {
    @Provides
    @Singleton
    fun provideDatabase(app:Application):TodoDatabase{
        var INSTANCE:TodoDatabase?=null
        if (INSTANCE ==null)
        {
            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    app,TodoDatabase::class.java,"TODO"
                ).createFromAsset("Todo.db").build()
            }
        }
        return INSTANCE!!
    }
    @Provides
    @Singleton
    fun provideRepository(db:TodoDatabase): TodoRepository{
        return TodoRepositoryImp(db.todoDao())
    }
}