package com.example.uaspam.model


import NetworkpemasokRepository
import PemasokRepository
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.repository.NetworkkategoriRepository
import com.example.uaspam.repository.NetworkprodukRepository
import com.example.uaspam.repository.ProdukRepository
import com.example.uaspam.service_api.KategoriService
import com.example.uaspam.service_api.PemasokService
import com.example.uaspam.service_api.ProdukService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val produkRepository : ProdukRepository
    val pemasokRepository : PemasokRepository
    val kategoriRepository : KategoriRepository
}

class ProdukContainer : AppContainer {

    private val json = Json { ignoreUnknownKeys = true }

    private val baseUrlproduk = "http://10.0.2.2:3000/api/produk/"
    private val baseUrlpemasok = "http://10.0.2.2:3000/api/pemasok/"
    private val baseUrlkategori = "http://10.0.2.2:3000/api/pemasok/"


    private val retrofitproduk: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlproduk)
        .build()

    private val retrofitpemasok: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlpemasok)
        .build()

    private val retrofitkategori: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlkategori)
        .build()

    private val produkService: ProdukService by lazy {
        retrofitproduk.create(ProdukService::class.java) // Nama benar
    }

    override val produkRepository: ProdukRepository by lazy {
        NetworkprodukRepository(produkService)
    }

    private val pemasokService: PemasokService by lazy {
        retrofitpemasok.create(PemasokService::class.java) // Nama benar
    }

    override val pemasokRepository: PemasokRepository by lazy {
        NetworkpemasokRepository(pemasokService)
    }

    private val kategoriService: KategoriService by lazy {
        retrofitkategori.create(KategoriService::class.java) // Nama benar

    }
    override val kategoriRepository: KategoriRepository by lazy {
        NetworkkategoriRepository(kategoriService)
    }
}
