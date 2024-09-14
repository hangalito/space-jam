package com.hangalo.spacejam.ui

import android.content.Context
import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangalo.spacejam.domain.data.remote.apod.APODRepository
import com.hangalo.spacejam.ui.screens.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SpaceJamViewModel(private val repository: APODRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val yearRange: IntRange = 1995..Calendar.getInstance().get(Calendar.YEAR)
    val currentDateMillis: () -> Long = { Calendar.getInstance().timeInMillis }

    var showingDatePicker: Boolean by mutableStateOf(false)
        private set
    var showingDateRangePicker: Boolean by mutableStateOf(false)
        private set
    var retry: () -> Unit = ::getTodayPicture

    init {
        getTodayPicture()
    }

    /*region description="UI Actions*/
    fun showDatePicker() {
        viewModelScope.launch { showingDatePicker = true }
    }

    fun hideDatePicker() {
        viewModelScope.launch { showingDatePicker = false }
    }

    fun showDateRangePicker() {
        viewModelScope.launch { showingDateRangePicker = true }
    }

    fun hideDateRangePicker() {
        viewModelScope.launch { showingDateRangePicker = false }
    }
    /*endregion*/

    fun getTodayPicture() {
        retry = ::getTodayPicture
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiState.Loading)
            try {
                val result = repository.getTodayPicture()
                _uiState.emit(UiState.Success.SinglePicture(result))
            } catch (ex: IOException) {
                _uiState.emit(UiState.Error(ex.localizedMessage.orEmpty()))
            } catch (ex: HttpException) {
                _uiState.emit(UiState.Error("${ex.response()?.message()}"))
            }
        }
    }

    fun getYesterdayPicture() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getYesterdayPicture()
                _uiState.emit(UiState.Success.SinglePicture(result))
            } catch (ex: IOException) {
                _uiState.emit(UiState.Error(ex.localizedMessage.orEmpty()))
            } catch (ex: HttpException) {
                _uiState.emit(UiState.Error("${ex.response()?.message()}"))
            }
        }
    }

    fun chooseDate(dateMillis: Long?, context: Context) {
        retry = { chooseDate(dateMillis, context) }
        hideDatePicker()

        if (dateMillis == null) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiState.Loading)
            try {
                val result = repository.getPictureByDate(dateMillis)
                _uiState.emit(UiState.Success.SinglePicture(result))
            } catch (ex: IOException) {
                _uiState.emit(UiState.Error(ex.localizedMessage.orEmpty()))
            } catch (ex: HttpException) {
                _uiState.emit(UiState.Error("${ex.response()?.message()}"))
            }
        }
    }

    fun getPictureIn(startDate: Long?, endDate: Long?, context: Context) {
        retry = { getPictureIn(startDate, endDate, context) }
        hideDateRangePicker()

        if ((startDate == null) || (endDate == null)) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getPicturesWithinInterval(startDate, endDate)
                _uiState.emit(UiState.Success.MultiplePictures(result))
            } catch (ex: IOException) {
                _uiState.emit(UiState.Error(ex.localizedMessage.orEmpty()))
            } catch (ex: HttpException) {
                _uiState.emit(UiState.Error("${ex.response()?.message()}"))
            }
        }
    }
}
