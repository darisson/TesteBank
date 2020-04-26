package com.domain.usercase.loginUserCase

import com.data.repository.AuthRepository
import com.data.source.remote.api.ApiResult
import com.data.source.remote.dto.ResponseLogin
import com.domain.usercase.BaseUserCasae.BaseUserCase

class LoginUserCase(private val authRepository: AuthRepository) :
    BaseUserCase<ResponseLogin, LoginUserCase.Params>() {

    override suspend fun run(param: Params): ApiResult<ResponseLogin> = authRepository.doLogin(param.user, param.password)

    data class Params(val user: String, val password:String)
}