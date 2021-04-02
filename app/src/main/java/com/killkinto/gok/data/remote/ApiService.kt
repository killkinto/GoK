package com.killkinto.gok.data.remote

import com.killkinto.gok.data.model.Products
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/sandbox/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

class ProductsApi(private val retrofit: Retrofit) {
    val retrofitService: GoKService by lazy {
        retrofit.create(GoKService::class.java)
    }
}

interface GoKService {
    @GET("products")
    suspend fun getProducts() : Products
}