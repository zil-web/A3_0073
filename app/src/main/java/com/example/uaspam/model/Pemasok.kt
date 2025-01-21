package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Pemasok (
    val id_pemasok: String,
    val nama_produk: String,
    val nama_pemasok: String,

    val alamat_pemasok: String,
    val telepon_pemasok: String,
)