package com.alexyach.kotlin.numbers.repository.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("/random/math?json=true")
    fun getNumberRandom(): Call<NumberDTO>

    @GET("/{number}/math?json=true")
    fun getByNumber(
        @Path("number") number: String
    ): Call<NumberDTO>
}