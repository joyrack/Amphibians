package com.example.amphibians.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

// creating the retrofit object
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()


/**
 * Creating an interface that defines how retrofit talks
 * to the web server using HTTP requests
 */
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * This object is the public singleton object that the rest of the app can access
 */
object AmphibiansApi {
    // using "lazy initialization" to avoid unnecessary computation
    // or use of other computing resources.
    // The object is only initialized when we actually need it
    val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
}