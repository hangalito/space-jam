package com.hangalo.spacejam.ui.screens.interval

import android.util.Log
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


class IntervalViewModel(private val repository: APODRepository) : ViewModel() {

    var uiState: IntervalUiState by mutableStateOf(IntervalUiState.Loading)
    var retryAction: () -> Unit = {}

    fun getPicturesFromDate(dateMillis: Long) {
        retryAction = { getPicturesFromDate(dateMillis) }
        uiState = IntervalUiState.Loading
        viewModelScope.launch {
            uiState = try {
                val date = Date(dateMillis).toString()
                IntervalUiState.Success(repository.getPicturesFrom(date))
            } catch (e: HttpException) {
                Log.d("HttpException", e.localizedMessage as String)
                IntervalUiState.DefaultError
            } catch (e: IOException) {
                Log.d("IOException", e.localizedMessage as String)
                IntervalUiState.DefaultError
            }
        }
    }

    fun getPictureInInterval(statDate: Long, endDate: Long) {
        retryAction = { getPictureInInterval(statDate, endDate) }
        uiState = IntervalUiState.Loading
        // TODO: write code to get data in specified interval
    }

}
