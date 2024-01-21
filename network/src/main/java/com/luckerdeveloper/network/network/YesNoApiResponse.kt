package com.luckerdeveloper.network.network

import kotlinx.serialization.Serializable

@Serializable
data class YesNoApiResponse(
    val answer: String,
    val forced: Boolean = false,
    val image: String,
)