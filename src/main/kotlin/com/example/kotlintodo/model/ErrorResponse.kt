package com.example.kotlintodo.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ErrorResponse(

    var resultCode: String?=null,
    var httpStatus: HttpStatus?=null,
    var httpMethod: String?=null,
    var message: String?=null,
    var path: String?=null,
    var timestamp: LocalDateTime?=null,
    var errors: MutableList<Error>?=null
)

data class Error(
    var field: String?=null,
    var message: String?=null,
    var value: Any?=null
)
