package com.data.repository

import com.data.source.remote.api.ApiResult
import com.data.source.remote.api.AuthApi
import com.data.source.remote.dto.ResponseLogin
import java.lang.Exception

class AuthRepository (private val authApi: AuthApi){

    fun doLogin(user:String, password:String) : ApiResult<ResponseLogin> {
        return try {
            val result = authApi.login(user, password).execute()
            if (result.isSuccessful) {
                result.body()?.let {
                    if (!it.error.code.isNullOrEmpty()) {
                        return ApiResult.Error(Throwable(it.error.message))
                    }
                    return ApiResult.Success(it)
                }
            }
            ApiResult.Error(Throwable("API Error"))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}