package com.hangalo.spacejam.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hangalo.spacejam.R
import com.hangalo.spacejam.ui.screens.NavigationRoute
import com.hangalo.spacejam.ui.screens.home.HomeScreen
import kotlinx.coroutines.launch


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
    val drawerState = rememberDrawerState(initialValue = Closed)
    val coroutineScope = rememberCoroutineScope()

    DismissibleNavigationDrawer(
        drawerContent = { SpaceJamDrawerSheet() },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                SpaceJamTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onMenuClick = { coroutineScope.launch { if (drawerState.isOpen) drawerState.close() else drawerState.open() } })
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
                    HomeScreen(modifier)
                }
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

@Composable
fun SpaceJamDrawerSheet(modifier: Modifier = Modifier) {
    DismissibleDrawerSheet(
        modifier = modifier.fillMaxHeight(),
        drawerShape = RoundedCornerShape(topEnd = 33.dp, bottomEnd = 33.dp),
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = typography.displaySmall,
            modifier = Modifier.padding(12.dp)
        )
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.today)) },
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_today),
                    contentDescription = null
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.yesterday)) },
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_yesterday),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.menu_choose_day)) },
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_select_date),
                    contentDescription = null
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.menu_date_range)) },
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_date_range),
                    contentDescription = null
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SpaceJamTopAppBarPreview() {
    SpaceJamTopAppBar()
}
