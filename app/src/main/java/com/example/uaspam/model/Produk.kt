package com.example.uaspam.model

import kotlinx.serialization.Serializable


@Serializable
data class Produk (
    val id_produk: Int,
    val nama_produk: String,
    val deskripsi_produk: String,
    val harga: Int,
    val stok: Int,
    val id_kategori: Int,
    val id_pemasok: Int,
    val id_merk: Int,
)


@Serializable
data class produkResponse(
    val status: Boolean,
    val message: String,
    val data: List<Produk>
)

@Serializable
data class produkResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Produk
)