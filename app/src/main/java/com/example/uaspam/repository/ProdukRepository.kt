package com.example.uaspam.repository

import com.example.uaspam.model.Produk
import com.example.uaspam.model.produkResponse
import com.example.uaspam.model.produkResponseDetail
import com.example.uaspam.service_api.ProdukService
import java.io.IOException

interface ProdukRepository {
    suspend fun getproduk(): produkResponse
    suspend fun insertproduk(produk: Produk)
    suspend fun updateproduk(id_produk: String, produk: Produk)
    suspend fun deleteproduk(id_produk: String)
    suspend fun getprodukByid_produk(id_produk: String): produkResponseDetail
}

class NetworkprodukRepository(
    private val produkApiService: ProdukService
) : ProdukRepository{

    override suspend fun insertproduk(produk: Produk) {
        produkApiService.insertproduk(produk)
    }

    override suspend fun updateproduk(id_produk: String, produk: Produk) {
        produkApiService.updateproduk(id_produk, produk)
    }

    override suspend fun deleteproduk(id_produk: String) {
        try {
            val response = produkApiService.deleteproduk(id_produk)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete produk. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getproduk(): produkResponse {
        return produkApiService.getproduk()
    }

    override suspend fun getprodukByid_produk(id_produk: String): produkResponseDetail {
        return  produkApiService.getprodukByid_produk(id_produk)
    }
}