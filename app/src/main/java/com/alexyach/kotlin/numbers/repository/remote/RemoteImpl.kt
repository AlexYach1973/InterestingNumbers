package com.alexyach.kotlin.numbers.repository.remote

import com.alexyach.kotlin.numbers.repository.ICallbackResponse
import com.alexyach.kotlin.numbers.repository.IGetDetailsByNumber
import com.alexyach.kotlin.numbers.utils.BASE_URL
import com.alexyach.kotlin.numbers.utils.converterNumberDTOToNumberModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RemoteImpl : IGetDetailsByNumber {

    private val serviceImpl = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().setLenient().create())
        )
    }

    private val api: ServiceApi = serviceImpl.build().create(ServiceApi::class.java)

    override fun getDetailsByNumber(
        number: String,
        callbackResponse: ICallbackResponse
    ) {

        api.getByNumber(number).enqueue(object : Callback<NumberDTO> {

            override fun onResponse(call: Call<NumberDTO>, response: Response<NumberDTO>) {

                if (response.body() == null) {
                    callbackResponse.callbackError(IOException("Немає інформації"))
                } else {
                    callbackResponse
                        .callbackSuccess(converterNumberDTOToNumberModel(response.body()!!))
                }
            }

            override fun onFailure(call: Call<NumberDTO>, t: Throwable) {
                callbackResponse.callbackError(t as Exception)
            }
        })
    }

    fun getRandomNumber(
        callbackResponse: ICallbackResponse
    ) {
        val result = api.getNumberRandom().execute()
        if (result.isSuccessful) {
            callbackResponse.callbackSuccess(
                converterNumberDTOToNumberModel(api.getNumberRandom().execute().body()!!)
            )
        } else {
            callbackResponse.callbackError(IOException("Помилка!"))
        }
    }
}