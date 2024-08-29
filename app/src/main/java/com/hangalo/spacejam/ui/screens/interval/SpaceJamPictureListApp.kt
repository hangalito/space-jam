package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDefaults.YearAbbrMonthDaySkeleton
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hangalo.spacejam.R
import com.hangalo.spacejam.domain.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.util.MenuSheet
import com.hangalo.spacejam.ui.util.MenuSheetActions
import java.lang.System.currentTimeMillis


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamPictureListApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: IntervalViewModel = viewModel(factory = Factory),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = currentTimeMillis(),
        initialSelectedEndDateMillis = currentTimeMillis(),
        initialDisplayMode = DisplayMode.Input
    )
    var isDialogVisible by remember { mutableStateOf(true) }

    ModalNavigationDrawer(drawerContent = {
        MenuSheet(
            actions = MenuSheetActions.defaultActions(
                viewModel,
                drawerState,
                coroutineScope,
                navController,
            ) {},
        )
    }) {
        Scaffold { innerPadding ->
            IntervalScreen(
                uiState = viewModel.uiState,
                retryAction = viewModel.retry,
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .verticalScroll(rememberScrollState())
            )
        }
    }

    when {
        isDialogVisible -> {
            DatePickerDialog(
                onDismissRequest = { isDialogVisible = false },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.getPictureInInterval(
                            dateRangePickerState.selectedStartDateMillis
                                ?: currentTimeMillis(),
                            dateRangePickerState.selectedEndDateMillis ?: currentTimeMillis()
                        )
                        isDialogVisible = false
                    }) {
                        Text(stringResource(id = R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { isDialogVisible = false }) {
                        Text(stringResource(id = R.string.cancel))
                    }
                },
            ) {
                DateRangePicker(
                    state = dateRangePickerState,
                    dateFormatter = DatePickerDefaults.dateFormatter(selectedDateDescriptionSkeleton = YearAbbrMonthDaySkeleton)
                )
            }
        }
    }

}
