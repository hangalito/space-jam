package com.hangalo.spacejam.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity("astronomic_pictures")
@Serializable
data class AstronomicPicture(
    @PrimaryKey val date: String,
    val explanation: String,
    @ColumnInfo("hdurl") @SerialName("hdurl") val hdUrl: String = "",
    @ColumnInfo("media_type") @SerialName("media_type") val mediaType: String,
    @ColumnInfo("service_version") @SerialName("service_version") val serviceName: String,
    val title: String,
    val url: String,
) {
    @ColumnInfo("copyright")
    @SerialName("copyright")
    var copyright: String = ""

    constructor(
        copyright: String,
        date: String,
        explanation: String,
        hdUrl: String,
        mediaType: String,
        serviceName: String,
        title: String,
        url: String,
    ) : this(date, explanation, hdUrl, mediaType, serviceName, title, url) {
        this.copyright = copyright
    }
}
