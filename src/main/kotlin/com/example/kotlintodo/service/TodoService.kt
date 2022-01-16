package com.example.kotlintodo.service

import com.example.kotlintodo.database.Todo
import com.example.kotlintodo.database.of
import com.example.kotlintodo.model.TodoDto
import com.example.kotlintodo.model.of
import com.example.kotlintodo.repository.TodoRepository
import org.springframework.stereotype.Service

/**
 * model Mapper
 * Kotlin Reflection
 */
@Service
class TodoService(
    val todoRepository: TodoRepository
) {

    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().of(it)
        }.let {
            todoRepository.save(it)
        }?.let {
            TodoDto().of(it)
        }
    }

    fun read(index: Int): TodoDto? {
        return todoRepository.findOne(index)?.let {
            TodoDto().of(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepository.findAll()
            .map {
                TodoDto().of(it)
            }.toMutableList()
    }

    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().of(it)
        }.let {
            todoRepository.save(it)
        }?.let {
            TodoDto().of(it)
        }
    }

    fun delete(index: Int): Boolean {
        return todoRepository.delete(index)
    }
}