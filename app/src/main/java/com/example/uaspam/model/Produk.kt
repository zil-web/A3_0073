package com.example.uaspam.model

import kotlinx.serialization.Serializable


@Serializable
data class Produk (
    val id_produk: String,
    val nama_produk: String,
    val deskripsi_produk: String,

    val stok: String,
    val id_kategori: String,
    val id_pemasok: String,
    val id_merk: String,
)