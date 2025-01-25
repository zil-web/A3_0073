package com.example.uaspam.service_api

import com.example.uaspam.model.Kategori
import com.example.uaspam.model.kategoriResponse
import com.example.uaspam.model.kategoriResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {
    @Headers(
        "Accept: application/json",
        "Content-Type:  application/json"
    )

    @GET(".")
    suspend fun getkategori(): kategoriResponse

    @GET("{id_kategori}")
    suspend fun getkategoriByid_kategori(@Path("id_kategori") id_kategori: String) : kategoriResponseDetail

    @POST("store")
    suspend fun insertkategori(@Body kategori: Kategori)

    @PUT("{id_kategori}")
    suspend fun updatekategori(@Path("id_kategori") id_kategori: String, @Body kategori: Kategori)

    @DELETE("{id_kategori}")
    suspend fun deletekategori(@Path("id_kategori") id_kategori: String) : Response<Void>
}