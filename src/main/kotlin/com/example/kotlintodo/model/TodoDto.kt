package com.example.kotlintodo.model

import com.example.kotlintodo.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDto(

    @field:ApiModelProperty(
        value = "DB Index",
        example = "1",
        required = false
    )
    var index: Int?=null,

    @field:ApiModelProperty(
        value = "일정명",
        example = "일정관리",
        required = true
    )
    @field:NotBlank
    var title: String?=null,

    @field:ApiModelProperty(
        value = "일정 설명",
        example = "13시 스타벅스",
        required = false
    )
    var description: String ?=null,

    @field:ApiModelProperty(
        value = "스케줄",
        example = "2021-11-01 11:00:11",
        required = true
    )
    @field:NotBlank
    var schedule: String?=null,

    var createAt: LocalDateTime?=null,

    var updateAt: LocalDateTime?=null
) {

    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss 포맷이 맞지 않습니다.")
    private fun validSchdule(): Boolean {
        return try {
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        } catch(e: Exception) {
            false
        }
    }
}

fun TodoDto.of(todo: Todo): TodoDto {
    return TodoDto().apply {
        this.index = todo.index
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createAt = todo.createAt
        this.updateAt = todo.updateAt
    }
}