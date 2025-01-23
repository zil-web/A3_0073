package com.example.uaspam.service_api

import com.example.uaspam.model.Produk
import com.example.uaspam.model.produkResponse
import com.example.uaspam.model.produkResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProdukService {
    @Headers(
        "Accept: application/json",
        "Content-Type:  application/json"
    )

    @GET(".")
    suspend fun getproduk(): produkResponse

    @GET("{id_produk}")
    suspend fun getprodukByid_produk(@Path("id_produk") id_produk: String) : produkResponseDetail

    @POST("store")
    suspend fun insertproduk(@Body produk: Produk)

    @PUT("{id_produk}")
    suspend fun updateproduk(@Path("id_produk") id_produk: String, @Body produk: Produk)

    @DELETE("{id_produk}")
    suspend fun deleteproduk(@Path("id_produk") id_produk: String) : Response<Void>
}