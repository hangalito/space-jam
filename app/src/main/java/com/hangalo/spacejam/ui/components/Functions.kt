package com.hangalo.spacejam.ui.components

import android.content.Context
import android.icu.text.DateFormat
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
