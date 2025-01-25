package com.example.uaspam.repository

import com.example.uaspam.model.Kategori
import com.example.uaspam.model.kategoriResponse
import com.example.uaspam.model.kategoriResponseDetail
import com.example.uaspam.service_api.KategoriService
import java.io.IOException

interface KategoriRepository {
    suspend fun getkategori(): kategoriResponse
    suspend fun insertkategori(kategori: Kategori)
    suspend fun updatekategori(id_kategori: String, kategori: Kategori)
    suspend fun deletekategori(id_kategori: String)
    suspend fun getkategoriByid_kategori(id_kategori: String): kategoriResponseDetail
}

class NetworkkategoriRepository(
    private val kategoriApiService: KategoriService
) : KategoriRepository {

    override suspend fun insertkategori(kategori: Kategori) {
        kategoriApiService.insertkategori(kategori)
    }

    override suspend fun updatekategori(id_kategori: String, kategori: Kategori) {
        kategoriApiService.updatekategori(id_kategori, kategori)
    }

    override suspend fun deletekategori(id_kategori: String) {
        try {
            val response = kategoriApiService.deletekategori(id_kategori)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete kategori. HTTP Status Code: " +
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

    override suspend fun getkategori(): kategoriResponse {
        return kategoriApiService.getkategori()
    }

    override suspend fun getkategoriByid_kategori(id_kategori: String): kategoriResponseDetail {
        return kategoriApiService.getkategoriByid_kategori(id_kategori)
    }
}