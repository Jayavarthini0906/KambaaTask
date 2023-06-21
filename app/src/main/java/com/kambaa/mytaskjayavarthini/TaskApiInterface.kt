package com.kambaa.mytaskjayavarthini

import retrofit2.Call
import retrofit2.http.GET

interface TaskApiInterface {

    @GET("products")
    fun getData():Call<List<ProductDataItem>>
}