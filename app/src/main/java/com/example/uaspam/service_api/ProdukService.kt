package com.example.uaspam.service_api

import com.example.uaspam.model.Produk
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProdukService {
    @Headers(
        "Accept: application/json",
        "Content-Type:  application/json"
    )

    @GET("bacaproduk.php")
    suspend fun getProduk():List<Produk>

    @GET("baca1produk.php/{id_produk}")
    suspend fun getProdukByid_produk(@Query("id_produk") id_produk: String) : Produk

    @POST("insertproduk.php")
    suspend fun insertProduk(@Body produk: Produk)

    @PUT("editproduk.php/{id_produk}")
    suspend fun editProduk(@Query("id_produk") id_produk: String, @Body produk: Produk)

    @DELETE("deleteproduk.php/")
    suspend fun deleteProduk(@Query("id_produk") id_produk: String) : Response<Void>
}