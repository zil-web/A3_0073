package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Kategori (
    val id_kategori: String,
    val nama_kategori: String,
    val deskripsi_kategori: String,
)

@Serializable
data class kategoriResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pemasok>
)

@Serializable
data class kategoriResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Pemasok
)