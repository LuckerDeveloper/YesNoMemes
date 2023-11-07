package com.luckerdeveloper.requestyesno.network

import kotlinx.serialization.Serializable

@Serializable
data class YesNoApiResponse(
    val answer: String,
    val forced: Boolean,
    val image: String,
)