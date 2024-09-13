package com.hangalo.spacejam.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.screens.NavigationRoute
import com.hangalo.spacejam.ui.screens.home.HomeScreen


/**
 * The main composable for the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    scrollBehavior: TopAppBarScrollBehavior = pinnedScrollBehavior()
) {

    Scaffold(
        topBar = { SpaceJamTopAppBar(scrollBehavior = scrollBehavior) }
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
                HomeScreen(modifier)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.app_name,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onMenuClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(stringResource(title)) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SpaceJamTopAppBarPreview() {
    SpaceJamTopAppBar()
}
