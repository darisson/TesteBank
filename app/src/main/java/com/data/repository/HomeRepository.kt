package com.data.repository

import com.data.source.remote.api.ApiResult
import com.data.source.remote.api.AuthApi
import com.domain.model.Statement
import java.lang.Exception

class HomeRepository (private val authApi: AuthApi){

    fun getStatements(id: String) : ApiResult<List<Statement>>{
        return try {
            val result = authApi.getStatements(id).execute()
            if (result.isSuccessful) { result.body()?.let { return ApiResult.Success(it.statementList) } }
            ApiResult.Error(Throwable("API Error"))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}