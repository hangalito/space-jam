package com.hangalo.spacejam.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hangalo.spacejam.R
import com.hangalo.spacejam.domain.container.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.components.SpaceJamDrawerSheet
import com.hangalo.spacejam.ui.components.SpaceJamTopAppBar
import com.hangalo.spacejam.ui.components.getSelectableDates
import com.hangalo.spacejam.ui.screens.NavigationRoute
import com.hangalo.spacejam.ui.screens.NavigationRoute.valueOf
import com.hangalo.spacejam.ui.screens.ScreenManager
import com.hangalo.spacejam.ui.screens.UiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: SpaceJamViewModel = viewModel(factory = Factory),
) {
    val uiState: UiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = Closed)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = viewModel.currentDateMillis(),
        selectableDates = getSelectableDates(),
        yearRange = viewModel.yearRange
    )
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = viewModel.currentDateMillis(),
        initialSelectedEndDateMillis = viewModel.currentDateMillis(),
        initialDisplayMode = DisplayMode.Input,
        selectableDates = getSelectableDates(),
        yearRange = viewModel.yearRange
    )
    val scrollBehavior = pinnedScrollBehavior()
    val context = LocalContext.current

    val backStackState by navController.currentBackStackEntryAsState()
    val title = valueOf(backStackState?.destination?.route ?: NavigationRoute.Home.name)


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SpaceJamDrawerSheet(
                onTodayClick = {
                    coroutineScope.launch { drawerState.close() }
                    viewModel.getTodayPicture()
                },
                onYesterdayClick = {
                    coroutineScope.launch { drawerState.close() }
                    viewModel.getYesterdayPicture()
                },
                onChooseDayClick = {
                    coroutineScope.launch { drawerState.close() }
                    viewModel.showDatePicker()
                },
                onDateRangeClick = {
                    coroutineScope.launch { drawerState.close() }
                    viewModel.showDateRangePicker()
                },
            )
        }
    ) {
        Scaffold(
            topBar = {
                SpaceJamTopAppBar(
                    title = title.title,
                    scrollBehavior = scrollBehavior,
                    onMenuClick = {
                        coroutineScope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    }
                )
            },
        ) { innerPadding: PaddingValues ->
            NavHost(
                navController = navController,
                startDestination = NavigationRoute.Home.name,
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .nestedScroll(connection = scrollBehavior.nestedScrollConnection)
                    .verticalScroll(rememberScrollState())
            ) {
                composable(route = NavigationRoute.Home.name) {
                    ScreenManager(uiState = uiState, onRetryClick = viewModel.retry)
                }
            }
        }
    }

    when {
        viewModel.showingDatePicker -> {
            DatePickerDialog(
                onDismissRequest = viewModel::hideDatePicker,
                confirmButton = {
                    TextButton(onClick = {
                        if (datePickerState.selectedDateMillis != null) {
                            viewModel.chooseDate(datePickerState.selectedDateMillis, context)
                        }
                    }) {
                        Text(stringResource(R.string.ok))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        viewModel.showingDateRangePicker -> {
            DatePickerDialog(
                onDismissRequest = viewModel::hideDateRangePicker,
                confirmButton = {
                    TextButton(onClick = {
                        val startDate = dateRangePickerState.selectedStartDateMillis
                        val endDate = dateRangePickerState.selectedEndDateMillis
                        viewModel.getPictureIn(startDate, endDate, context)
                    }) {
                        Text(stringResource(R.string.ok))
                    }
                }) {
                DateRangePicker(state = dateRangePickerState)
            }
        }
    }
}
