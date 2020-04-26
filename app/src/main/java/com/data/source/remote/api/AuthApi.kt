package com.data.source.remote.api

import com.data.source.remote.dto.ResponseLogin
import com.data.source.remote.dto.ResponseStatement
import retrofit2.Call
import retrofit2.http.*

interface AuthApi {

    @POST("login")
    @FormUrlEncoded
    fun loginAsync(@Field("user") user: String, @Field("password") password:String): Call<ResponseLogin>

    @GET("statements/{id}")
    fun getStatements(@Path("id") id: String): Call<ResponseStatement>

}