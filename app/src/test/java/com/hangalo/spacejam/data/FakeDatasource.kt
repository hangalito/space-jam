package com.hangalo.spacejam.data

import com.hangalo.spacejam.domain.AstronomicPicture
import java.sql.Date


object FakeDatasource {

    private val date = Date(System.currentTimeMillis()).toString().split("-")
    private val today = "${date[0]}-${date[1]}-${date[2]}"
    private val yesterday = "${date[0]}-${date[1]}-${date[2].toInt() - 1}"
    private val twoDaysAgo = "${date[0]}-${date[1]}-${date[2].toInt() - 2}"

    val astronomicPictures: MutableList<AstronomicPicture> = mutableListOf(
        AstronomicPicture(date = today, "", "", "", "", "", ""),
        AstronomicPicture(date = yesterday, "", "", "", "", "", ""),
        AstronomicPicture(date = twoDaysAgo, "", "", "", "", "", ""),
        AstronomicPicture(date = "2004-11-10", "", "", "", "", "", ""),
    )

    fun today(): AstronomicPicture {
        return astronomicPictures.first()
    }

    fun yesterday(): AstronomicPicture {
        return astronomicPictures[1]
    }

    fun twoDaysAgo(): AstronomicPicture {
        return astronomicPictures[2]
    }

    fun getByDate(dateMillis: Long): AstronomicPicture {
        var pic: AstronomicPicture? = null
        astronomicPictures.forEach {
            if (it.date == Date(dateMillis).toString())
                pic = it
        }
        return pic!!
    }
}
