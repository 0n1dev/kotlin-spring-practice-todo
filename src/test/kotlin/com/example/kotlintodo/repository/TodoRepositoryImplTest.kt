package com.example.kotlintodo.repository

import com.example.kotlintodo.config.AppConfig
import com.example.kotlintodo.database.Todo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
internal class TodoRepositoryImplTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before() {
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "테스트 아아아아ㅏ"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        assertEquals(0, result?.index)
        assertEquals("테스트 아아아아ㅏ", result?.title)
        assertEquals("테스트", result?.description)
        assertNotNull(result?.createAt)
        assertNotNull(result?.updateAt)
    }

    @Test
    fun saveAllTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 1"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 2"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 3"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        assertTrue(result)
    }

    @Test
    fun findOneTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 1"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 2"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 3"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(1)

        assertNotNull(result)
        assertEquals(1, result?.index)
    }

    @Test
    fun updateTest() {
        val newResult = todoRepositoryImpl.save(
            Todo().apply {
                this.title = "테스트 ne"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        val updateResult = todoRepositoryImpl.save(
            Todo().apply {
                this.index = 0
                this.title = "테스트 update"
                this.description = "테스트B"
                this.schedule = LocalDateTime.now()
            }
        )

        assertEquals(newResult?.index, updateResult?.index)
    }
}