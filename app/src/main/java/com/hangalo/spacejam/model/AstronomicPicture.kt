package com.hangalo.spacejam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Entity("astronomic_picture")
@Serializable
data class AstronomicPicture(
    val date: String,
    val explanation: String,
    @ColumnInfo("hdurl") @SerialName("hdurl") val hdUrl: String,
    @ColumnInfo("media_type") @SerialName("media_type") val mediaType: String,
    @ColumnInfo("service_version") @SerialName("service_version") val serviceName: String,
    val title: String,
    val url: String,
)
