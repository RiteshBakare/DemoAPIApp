package com.gmail.at.riteshbakare420.api

import com.gmail.at.riteshbakare420.models.Data
import com.gmail.at.riteshbakare420.models.DemoModels
import com.gmail.at.riteshbakare420.models.Sample
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @GET("demo/getData")
    fun getAllData() : Call<DemoModels>

    @POST("demo/addData")
    fun addData(@Body sample: Sample) : Call<Sample>

    @GET("my")
    fun getText() : Call<String>
}