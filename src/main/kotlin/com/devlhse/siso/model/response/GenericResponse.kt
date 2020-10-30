package com.devlhse.siso.model.response

data class GenericResponse<T>(
        val code: Int,
        val status: String,
        val data: T
)