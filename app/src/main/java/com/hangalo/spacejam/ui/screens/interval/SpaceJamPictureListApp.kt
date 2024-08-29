package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDefaults.YearAbbrMonthDaySkeleton
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode.Companion.Input
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
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
import com.hangalo.spacejam.domain.container.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.util.MenuSheet
import com.hangalo.spacejam.ui.util.MenuSheetActions.Companion.defaultActions
import java.lang.System.currentTimeMillis


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamPictureListApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: IntervalViewModel = viewModel(factory = Factory),
) {
    val scrollBehavior = enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(initialValue = Closed)
    val datePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = currentTimeMillis(),
        initialSelectedEndDateMillis = currentTimeMillis(),
        initialDisplayMode = Input
    )
    var showingDialog by remember { mutableStateOf(true) }

    ModalNavigationDrawer(drawerContent = {
        MenuSheet(
            actions = defaultActions(
                viewModel = vm,
                drawerState = drawerState,
                coroutineScope = rememberCoroutineScope(),
                navController = navController,
            ) {},
        )
    }) {
        Scaffold { innerPadding ->
            IntervalScreen(
                uiState = vm.uiState,
                retryAction = vm.retry,
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .verticalScroll(rememberScrollState())
            )
        }
    }

    when {
        showingDialog -> {
            DatePickerDialog(
                onDismissRequest = { showingDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        vm.getPictureInInterval(
                            datePickerState.selectedStartDateMillis ?: currentTimeMillis(),
                            datePickerState.selectedEndDateMillis ?: currentTimeMillis()
                        )
                        showingDialog = false
                    }) {
                        Text(stringResource(id = R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showingDialog = false }) {
                        Text(stringResource(id = R.string.cancel))
                    }
                },
            ) {
                DateRangePicker(
                    state = datePickerState,
                    dateFormatter = DatePickerDefaults.dateFormatter(selectedDateDescriptionSkeleton = YearAbbrMonthDaySkeleton)
                )
            }
        }
    }
}
