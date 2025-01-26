package com.example.uaspam.repository

import com.example.uaspam.model.Merk
import com.example.uaspam.model.merkResponse
import com.example.uaspam.model.merkResponseDetail
import com.example.uaspam.service_api.MerkService
import java.io.IOException

interface MerkRepository {
    suspend fun getmerk(): merkResponse
    suspend fun insertmerk(merk: Merk)
    suspend fun updatemerk(id_merk: String, merk: Merk)
    suspend fun deletemerk(id_merk: String)
    suspend fun getmerkByid_merk(id_merk: String): merkResponseDetail
}

class NetworkmerkRepository(
    private val merkApiService: MerkService
) : MerkRepository {

    override suspend fun insertmerk(merk: Merk) {
        merkApiService.insertmerk(merk)
    }

    override suspend fun updatemerk(id_merk: String, merk: Merk) {
        merkApiService.updatemerk(id_merk, merk)
    }

    override suspend fun deletemerk(id_merk: String) {
        try {
            val response = merkApiService.deletemerk(id_merk)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete merk. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getmerk(): merkResponse {
        return merkApiService.getmerk()
    }

    override suspend fun getmerkByid_merk(id_merk: String): merkResponseDetail {
        return merkApiService.getmerkByid_merk(id_merk)
    }
}