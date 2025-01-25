package com.example.uaspam.service_api


import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.pemasokResponse
import com.example.uaspam.model.pemasokResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PemasokService {
    @Headers(
        "Accept: application/json",
        "Content-Type:  application/json"
    )

    @GET(".")
    suspend fun getpemasok(): pemasokResponse

    @GET("{id_pemasok}")
    suspend fun getpemasokByid_pemasok(@Path("id_pemasok") id_pemasok: String) : pemasokResponseDetail

    @POST("store")
    suspend fun insertpemasok(@Body pemasok: Pemasok)

    @PUT("{id_pemasok}")
    suspend fun updatepemasok(@Path("id_pemasok") id_pemasok: String, @Body pemasok: Pemasok)

    @DELETE("{id_pemasok}")
    suspend fun deletepemasok(@Path("id_pemasok") id_pemasok: String) : Response<Void>
}