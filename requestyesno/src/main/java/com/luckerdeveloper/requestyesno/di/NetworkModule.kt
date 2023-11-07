package com.luckerdeveloper.requestyesno.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.luckerdeveloper.requestyesno.network.YesNoService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideYesNoService(): com.luckerdeveloper.requestyesno.network.YesNoService {
        return Retrofit.Builder()
            .baseUrl("https://yesno.wtf/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(com.luckerdeveloper.requestyesno.network.YesNoService::class.java)
    }
}
