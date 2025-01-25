package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Pemasok (
    val id_pemasok: Int,
    val nama_produk: String,
    val nama_pemasok: String,
    val alamat_pemasok: String,
    val telepon_pemasok: String,
)

@Serializable
data class pemasokResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pemasok>
)

@Serializable
data class pemasokResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Pemasok
)