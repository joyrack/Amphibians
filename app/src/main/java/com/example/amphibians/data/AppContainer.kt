package com.example.amphibians.data

import com.example.amphibians.networking.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

/**
 * This class will contain all the dependencies that will be needed
 * throughout the application
 */
class AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * We don't need to create a singleton object for this anymore because there will be only
     * one instance of the AppContainer throughout our application
     * using "lazy initialization" to avoid unnecessary computation
     * or use of other computing resources.
     */
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    /**
     * The repository instance that will be passed to the view model
     */
    val repository: AmphibiansRepository by lazy {
        AmphibiansRepository(retrofitService)
    }
}