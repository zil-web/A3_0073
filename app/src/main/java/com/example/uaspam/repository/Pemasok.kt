package com.example.uaspam.repository

import com.example.uaspam.model.Pemasok
import java.io.IOException

interface PemasokRepository {
    suspend fun getPemasok(): List<Pemasok>
    suspend fun insertPemasok(pemasok: Pemasok)
    suspend fun updatePemasok(id_pemasok: String, mahasiswa: Pemasok)
    suspend fun deletePemasok(id_pemasok: String)
    suspend fun getPemasokByid_pemasok(id_pemasok: String): Pemasok
}

class NetworkMahasiswaRepository(
    private val pemasokApiService: PemasokService
):PemasokRepository{

    override suspend fun getPemasokByid_pemasok(id_pemasok: String): Pemasok {
        return pemasokApiService.getMahasiswaByNIM(id_pemasok)
    }

    override suspend fun getPemasok(): List<Pemasok> = pemasokApiService.getPemasok()

    override suspend fun insertPemasok(pemasok: Pemasok) {
        pemasokApiService.insertMahasiswa(pemasok)
    }

    override suspend fun updatePemasok(nim: String, pemasok: Pemasok) {
        pemasokApiService.editMahasiswa(nim,pemasok)
    }


    override suspend fun deletePemasok(id_pemasok: String) {
        try {
            val response = pemasokApiService.deleteMahasiswa(id_pemasok)
            if(!response.isSuccessful){
                throw IOException("Failed to delete mahasiswa. HTTP Status Code:" +
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