package com.luckerdeveloper.network.network

import retrofit2.http.GET

interface YesNoService {

    @GET("api")
    suspend fun getYesNo(): YesNoApiResponse
}