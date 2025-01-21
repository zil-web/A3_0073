package com.example.uaspam.service_api

import com.example.uaspam.model.Pemasok
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PemasokService {
    @Headers(
        "Accept: application/json",
        "Content-Type:  application/json"
    )

    @GET("bacapemasok.php")
    suspend fun getPemasok():List<Pemasok>

    @GET("baca1pemasok.php/{id_pemasok}")
    suspend fun getPemasokByid_pemasok(@Query("id_pemasok") id_pemasok: String) : Pemasok

    @POST("insertpemasok.php")
    suspend fun insertPemasok(@Body pemasok: Pemasok)

    @PUT("editpemasok.php/{id_pemasok}")
    suspend fun editPemasok(@Query("id_pemasok") id_pemasok: String, @Body pemasok: Pemasok)

    @DELETE("deletepemasok.php/")
    suspend fun deletePemasok(@Query("id_pemasok") id_pemasok: String) : Response<Void>
}