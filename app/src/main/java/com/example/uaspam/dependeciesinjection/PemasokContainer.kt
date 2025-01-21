package com.example.uaspam.model


import com.example.uaspam.repository.NetworkPemasokRepository
import com.example.uaspam.repository.PemasokRepository
import com.example.uaspam.service_api.PemasokService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val pemasokRepository: PemasokRepository
}

class PemasokContainer: AppContainer{
    private val baseUrl = "http://10.0.2.2:8000/umyTI/"
    private val json = Json{ignoreUnknownKeys = true}
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory ("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pemasokService: PemasokService by lazy {
        retrofit.create(PemasokService::class.java)}


    override val pemasokRepository: PemasokRepository by lazy {
        NetworkPemasokRepository(pemasokService)
    }
}