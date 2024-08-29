package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangalo.spacejam.data.remote.apod.APODRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.sql.Date
import android.util.Log.d as debug


class IntervalViewModel(private val repository: APODRepository) : ViewModel() {

    var uiState: IntervalUiState by mutableStateOf(IntervalUiState.Loading)
    var retry: () -> Unit = {}

    fun getPicturesFromDate(dateMillis: Long) {
        retry = { getPicturesFromDate(dateMillis) }
        uiState = IntervalUiState.Loading
        viewModelScope.launch {
            uiState = try {
                val date = Date(dateMillis).toString()
                IntervalUiState.Success(repository.getPicturesFrom(date))
            } catch (exception: HttpException) {
                debug("HttpException", exception.localizedMessage as String)
                IntervalUiState.DefaultError
            } catch (exception: IOException) {
                debug("IOException", exception.localizedMessage as String)
                IntervalUiState.DefaultError
            }
        }
    }

    fun getPictureInInterval(startDate: Long, endDate: Long) {
        retry = { getPictureInInterval(startDate, endDate) }
        uiState = IntervalUiState.Loading
        // TODO: write code to get data in specified interval
    }

}
