package com.domain.usercase.HomeUserCase

import com.data.repository.HomeRepository
import com.data.source.remote.api.ApiResult
import com.domain.model.Statement
import com.domain.usercase.BaseUserCasae.BaseUserCase

class HomeUserCase(private val homeRepository: HomeRepository) : BaseUserCase<List<Statement>, HomeUserCase.Params>() {

    override suspend fun run(param: Params): ApiResult<List<Statement>> = homeRepository.getStatements(param.userID)
    data class Params(val userID: String)
}