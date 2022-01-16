package com.example.kotlintodo.controller.api

import com.example.kotlintodo.model.TodoDto
import com.example.kotlintodo.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(description = "일정 관리")
@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService
) {

    @ApiOperation(value = "일정 확인", notes = "일정 확인 GET API")
    @GetMapping
    fun read(
        @ApiParam(name = "index")
        @RequestParam(required = false) index: Int?
    ): ResponseEntity<TodoDto>? {

        return index?.let {
            todoService.read(it)
        }?.let {
            ResponseEntity.ok(it)
        }?: kotlin.run {
            ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo/all")
                .build()
        }
    }

    @GetMapping("/all")
    fun readAll(): MutableList<TodoDto> {

        return todoService.readAll()
    }

    @PostMapping
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    @PutMapping
    fun update(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.update(todoDto)
    }

    @DeleteMapping("/{index}")
    fun delete(@PathVariable index: Int): ResponseEntity<Nothing> {

        if (!todoService.delete(index)) {
            return ResponseEntity.status(500).build<Nothing>()
        }

        return ResponseEntity.ok().build<Nothing>()
    }
}