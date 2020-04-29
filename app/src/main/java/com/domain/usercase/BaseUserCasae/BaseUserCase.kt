package com.domain.usercase.BaseUserCasae

import com.data.source.remote.api.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUserCase <Type, in Params> where Type : Any{

    abstract suspend fun execute(param: Params): ApiResult<Type>

    operator fun invoke(param: Params, onResult: (ApiResult<Type>) -> Unit = {}) {
        CoroutineScope(Dispatchers.IO).launch {
            val job = async { execute(param) }
            CoroutineScope(Dispatchers.Main).launch {
                onResult(job.await())
            }
        }
    }
}