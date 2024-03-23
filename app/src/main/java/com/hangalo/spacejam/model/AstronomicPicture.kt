package com.hangalo.spacejam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AstronomicPicture(
    val date: String,
    val explanation: String,
    @SerialName("hdrurl") val hdUrl: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("service_version") val serviceName: String,
    val title: String,
    val url: String,
)
