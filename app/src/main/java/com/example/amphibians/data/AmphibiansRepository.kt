package com.example.amphibians.data

import com.example.amphibians.networking.Amphibian
import com.example.amphibians.networking.AmphibiansApiService

class AmphibiansRepository(
    private val retrofitService: AmphibiansApiService
) {
    suspend fun getAmphibiansRepo() : List<Amphibian>? {
        return try {
            retrofitService.getAmphibians()
        } catch (e : Exception) {
            null
        }
    }
}