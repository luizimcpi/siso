package com.devlhse.siso.application.web.controller

import com.devlhse.siso.domain.exception.NotFoundException
import com.devlhse.siso.domain.exception.UnauthorizedException
import com.devlhse.siso.domain.extensions.removeQuotation
import com.devlhse.siso.domain.model.response.GenericResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionController {

    val log: Logger = LoggerFactory.getLogger(GlobalExceptionController::class.java)

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validationHandler(exception: MethodArgumentNotValidException):
            ResponseEntity<GenericResponse<MutableList<String>>> {
        log.error("Errors: ${exception.message}")

        val fieldErrors = exception.bindingResult.fieldErrors
        val globalErrors = exception.bindingResult.globalErrors

        val resultErrors = mutableListOf<String>()

        for(error in fieldErrors){
            resultErrors.add("${error.field}: ${error.defaultMessage?.removeQuotation()}")
        }

        for(error in globalErrors){
            resultErrors.add("${error.objectName}: ${error.defaultMessage?.removeQuotation()}")
        }

        return ResponseEntity.badRequest().body(
                GenericResponse(
                    code = HttpStatus.BAD_REQUEST.value(),
                    status = HttpStatus.BAD_REQUEST.toString(),
                    data = resultErrors
                )
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorizedExceptionHandler(exception: UnauthorizedException): ResponseEntity<GenericResponse<String>> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
                GenericResponse(
                        code = HttpStatus.UNAUTHORIZED.value(),
                        status = HttpStatus.UNAUTHORIZED.toString(),
                        data = "Token is not preset or invalid!"
                )
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundExceptionHandler(exception: NotFoundException): ResponseEntity<GenericResponse<String>> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                GenericResponse(
                        code = HttpStatus.NOT_FOUND.value(),
                        status = HttpStatus.NOT_FOUND.toString(),
                        data = exception.message.toString()
                )
        )
    }
}
