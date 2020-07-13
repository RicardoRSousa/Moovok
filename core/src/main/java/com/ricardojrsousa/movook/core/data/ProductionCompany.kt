package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?
)