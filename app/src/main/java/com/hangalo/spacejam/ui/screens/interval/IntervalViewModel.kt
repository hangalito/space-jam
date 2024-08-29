package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangalo.spacejam.domain.data.remote.apod.APODRepository
import com.hangalo.spacejam.ui.screens.interval.IntervalUiState.DefaultError
import com.hangalo.spacejam.ui.screens.interval.IntervalUiState.Loading
import com.hangalo.spacejam.ui.screens.interval.IntervalUiState.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import android.util.Log.d as debug


class IntervalViewModel(private val repository: APODRepository) : ViewModel() {

    var uiState: IntervalUiState by mutableStateOf(Loading)
    var retry: () -> Unit = {}

    /**
     * Start fetching pictures in the selected interval.
     */
    fun getPictureInInterval(startDate: Long, endDate: Long) {
        retry = { getPictureInInterval(startDate, endDate) }
        uiState = Loading
        viewModelScope.launch {
            uiState = try {
                Success(repository.getPicturesWithinInterval(startDate, endDate))
            } catch (exception: HttpException) {
                debugException(exception)
                DefaultError
            } catch (exception: IOException) {
                debugException(exception)
                DefaultError
            }
        }
    }

    private fun debugException(exception: Throwable) {
        val tag = exception::class.java.name
        var msg = exception.localizedMessage as String
        var code = ""
        if (exception is HttpException) {
            code = exception.response()?.message() ?: "0"
            msg = exception.message()
        }
        debug(tag, "$code - $msg")
    }

}
