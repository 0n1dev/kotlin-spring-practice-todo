package com.example.kotlintodo.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.validation.Validation

internal class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {

        val todoDto = TodoDto().apply {
            this.title = "efef"
            this.description = "wefwef"
            this.schedule = "2022-01-16 12:00:00"
        }

        val result = validator.validate(todoDto)

        result.forEach {
            println(it.invalidValue)
        }

        assertEquals(true, result.isEmpty())
    }
}