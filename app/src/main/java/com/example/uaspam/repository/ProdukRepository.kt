package com.example.uaspam.repository

import com.example.uaspam.model.Produk
import com.example.uaspam.service_api.ProdukService
import java.io.IOException

interface ProdukRepository {
    suspend fun getProduk(): List<Produk>
    suspend fun insertProduk(produk: Produk)
    suspend fun updateProduk(id_produk: String, pproduk: Produk)
    suspend fun deleteProduk(id_produk: String)
    suspend fun getProdukByid_produk(id_produk: String): Produk
}

class NetworkProdukRepository(
    private val produkApiService: ProdukService
):ProdukRepository{

    override suspend fun getProdukByid_produk(id_produk: String): Produk {
        return produkApiService.getProdukByid_produk(id_produk)
    }

    override suspend fun getProduk(): List<Produk> = produkApiService.getProduk()

    override suspend fun insertProduk(produk: Produk) {
        produkApiService.insertProduk(produk)
    }

    override suspend fun updateProduk(id_produk: String, produk: Produk) {
        produkApiService.editProduk(id_produk,produk)
    }


    override suspend fun deleteProduk(id_produk: String) {
        try {
            val response = produkApiService.deleteProduk(id_produk)
            if(!response.isSuccessful){
                throw IOException("Failed to delete produk. HTTP Status Code:" +
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