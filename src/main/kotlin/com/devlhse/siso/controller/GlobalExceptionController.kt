package com.devlhse.siso.controller

import com.devlhse.siso.model.response.GenericResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): GenericResponse<String> {
        return GenericResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                status = HttpStatus.BAD_REQUEST.toString(),
                data = constraintViolationException.message!!
        )
    }
}