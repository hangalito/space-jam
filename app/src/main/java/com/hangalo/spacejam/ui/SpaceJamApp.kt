package com.hangalo.spacejam.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.screens.home.HomeScreen
import com.hangalo.spacejam.ui.screens.home.HomeViewModel
import com.hangalo.spacejam.ui.screens.utils.MenuSheet
import com.hangalo.spacejam.ui.screens.utils.MenuSheetActions
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamApp(
    vModel: HomeViewModel = viewModel(factory = Factory),
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val actions = MenuSheetActions()
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = { MenuSheet(actions = actions) },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SpaceJamTopBar(scrollBehavior) {
                    coroutineScope.launch { drawerState.open() }
                }
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
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SpaceJamTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onMenuClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.TwoTone.Menu, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
private fun SpaceJamAppPreview() {
    SpaceJamApp()
}
