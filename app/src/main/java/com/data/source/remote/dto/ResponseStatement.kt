package com.data.source.remote.dto

import com.domain.model.Statement

data class ResponseStatement (
    val statementList: MutableList<Statement>
)
