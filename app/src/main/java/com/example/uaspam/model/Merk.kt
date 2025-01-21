package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Merk (
    val id_merk: String,
    val nama_merk: String,
    val deskripsi_merk: String,
)