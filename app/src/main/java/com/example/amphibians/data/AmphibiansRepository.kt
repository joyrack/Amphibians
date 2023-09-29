package com.example.amphibians.data

import com.example.amphibians.networking.Amphibian
import com.example.amphibians.networking.AmphibiansApi

class AmphibiansRepository {
    suspend fun getAmphibiansRepo() : List<Amphibian>? {
        return try {
            AmphibiansApi.retrofitService.getAmphibians()
        } catch (e : Exception) {
            null
        }
    }
}