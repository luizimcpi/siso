package com.devlhse.siso.domain.extensions

fun String.removeQuotation(): String =
        this.replace("\"", "")