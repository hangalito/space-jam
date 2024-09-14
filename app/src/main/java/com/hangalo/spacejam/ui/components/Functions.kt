package com.hangalo.spacejam.ui.components

import android.content.Context
import android.icu.text.DateFormat
import android.icu.util.Calendar
import android.icu.util.Calendar.JUNE
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import coil.ImageLoader
import coil.request.CachePolicy.ENABLED
import coil.request.ImageRequest
import com.hangalo.spacejam.R.drawable.ic_broken_img
import com.hangalo.spacejam.R.drawable.ic_img_placeholder
import java.sql.Date.valueOf
import java.util.Date

fun imageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .crossfade(true)
        .crossfade(300)
        .error(ic_broken_img)
        .fallback(ic_broken_img)
        .placeholder(ic_img_placeholder)
        .memoryCachePolicy(ENABLED)
        .build()
}

fun imageRequest(context: Context, url: String): ImageRequest {
    return ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .crossfade(300)
        .error(ic_broken_img)
        .fallback(ic_broken_img)
        .placeholder(ic_img_placeholder)
        .memoryCachePolicy(ENABLED)
        .build()
}

fun dateFormat(dateTime: String): String {
    return dateFormat(valueOf(dateTime).time)
}

fun dateFormat(time: Long): String {
    return DateFormat.getDateInstance().format(Date(time))
}

@OptIn(ExperimentalMaterial3Api::class)
fun getSelectableDates(): SelectableDates {
    val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    val currentDate: Long = Calendar.getInstance().timeInMillis
    val initialDate: Long = Calendar.getInstance().apply {
        clear()
        set(1995, JUNE, 16)
    }.timeInMillis
    return object : SelectableDates {

        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis in initialDate..currentDate
        }

        override fun isSelectableYear(year: Int): Boolean {
            return year in 1995..currentYear
        }

    }
}
