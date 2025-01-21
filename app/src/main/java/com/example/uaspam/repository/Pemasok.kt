package com.example.uaspam.repository

import com.example.uaspam.model.Pemasok
import com.example.uaspam.service_api.PemasokService
import java.io.IOException

interface PemasokRepository {
    suspend fun getPemasok(): List<Pemasok>
    suspend fun insertPemasok(pemasok: Pemasok)
    suspend fun updatePemasok(id_pemasok: String, pemasok: Pemasok)
    suspend fun deletePemasok(id_pemasok: String)
    suspend fun getPemasokByid_pemasok(id_pemasok: String): Pemasok
}

class NetworkPemasokRepository(
    private val pemasokApiService: PemasokService
):PemasokRepository{

    override suspend fun getPemasokByid_pemasok(id_pemasok: String): Pemasok {
        return pemasokApiService.getPemasokByid_pemasok(id_pemasok)
    }

    override suspend fun getPemasok(): List<Pemasok> = pemasokApiService.getPemasok()

    override suspend fun insertPemasok(pemasok: Pemasok) {
        pemasokApiService.insertPemasok(pemasok)
    }

    override suspend fun updatePemasok(id_pemasok: String, pemasok: Pemasok) {
        pemasokApiService.editPemasok(id_pemasok,pemasok)
    }


    override suspend fun deletePemasok(id_pemasok: String) {
        try {
            val response = pemasokApiService.deletePemasok(id_pemasok)
            if(!response.isSuccessful){
                throw IOException("Failed to delete pemasok. HTTP Status Code:" +
                        "${response.code()}")
            } else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }
}