package com.ricardojrsousa.movook.core.data

data class Book(
    override val id: String,
    val selfLink: String,
    val volumeInfo: Volume
) : Identifiable