package com.example.mytodoapp.TodoDatabase.Repository

import androidx.lifecycle.LiveData
import com.example.mytodoapp.TodoDatabase.TodoDao
import com.example.mytodoapp.TodoDatabase.Todo

class TodoRepositoryImp(val todoDao: TodoDao):TodoRepository{
    override suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return todoDao.getTodoById(id)
    }

    override fun getList(): LiveData<List<Todo>> {
        return  todoDao.getTodo()
    }
}

