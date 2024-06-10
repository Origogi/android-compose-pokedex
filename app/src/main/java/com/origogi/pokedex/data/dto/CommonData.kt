package com.origogi.pokedex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NameUrlData(
    val name: String,
    val url: String
)

@Serializable
data class UrlData(
    val url: String

)