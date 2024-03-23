package com.hangalo.spacejam.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.screens.HomeScreen
import com.hangalo.spacejam.ui.screens.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamApp(
    vModel: HomeViewModel = viewModel(factory = Factory),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding: PaddingValues ->
        HomeScreen(
            uiState = vModel.uiState,
            retryAction = vModel::getTodayPicture,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
