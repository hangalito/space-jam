package com.hangalo.spacejam.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hangalo.spacejam.R
import com.hangalo.spacejam.domain.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.utils.MenuSheet
import com.hangalo.spacejam.ui.utils.MenuSheetActions
import com.hangalo.spacejam.ui.utils.SpaceJamTopBar
import com.hangalo.spacejam.ui.utils.defaultActions
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamHome(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = Factory),
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

    var isVisible: Boolean by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerContent = {
            MenuSheet(
                actions = MenuSheetActions.defaultActions(
                    viewModel,
                    drawerState,
                    coroutineScope,
                    navController
                ) {
                    isVisible = true
                },
                modifier = modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SpaceJamTopBar(scrollBehavior) {
                    coroutineScope.launch { drawerState.open() }
                }
            }
        ) { innerPadding: PaddingValues ->
            HomeScreen(
                homeUiState = viewModel.homeUiState,
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
        isVisible -> {
            DatePickerDialog(
                onDismissRequest = { isVisible = false },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.getPictureByDate(datePickerState.selectedDateMillis!!)
                            isVisible = false
                        },
                    ) {
                        Text(stringResource(R.string.ok))
                    }
                },
                dismissButton = {
                    Button(onClick = { isVisible = false }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Preview
@Composable
private fun SpaceJamAppPreview() {
    SpaceJamHome(rememberNavController())
}
