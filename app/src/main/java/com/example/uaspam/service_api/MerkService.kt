package com.example.uaspam.service_api

import com.example.uaspam.model.Merk
import com.example.uaspam.model.merkResponse
import com.example.uaspam.model.merkResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MerkService {
    @Headers(
        "Accept: application/json",
        "Content-Type:  application/json"
    )

    @GET(".")
    suspend fun getmerk(): merkResponse

    @GET("{id_merk}")
    suspend fun getmerkByid_merk(@Path("id_merk") id_merk: String) : merkResponseDetail

    @POST("store")
    suspend fun insertmerk(@Body merk: Merk)

    @PUT("{id_merk}")
    suspend fun updatemerk(@Path("id_merk") id_merk: String, @Body merk: Merk)

    @DELETE("{id_merk}")
    suspend fun deletemerk(@Path("id_merk") id_merk: String) : Response<Void>
}