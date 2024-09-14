package com.hangalo.spacejam.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.R

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
fun SpaceJamDrawerSheet(
    modifier: Modifier = Modifier,
    onTodayClick: () -> Unit = {},
    onYesterdayClick: () -> Unit = {},
    onChooseDayClick: () -> Unit = {},
    onDateRangeClick: () -> Unit = {}
) {
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
            onClick = onTodayClick,
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
            onClick = onYesterdayClick,
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
            onClick = onChooseDayClick,
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
            onClick = onDateRangeClick,
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

@Preview
@Composable
private fun DismissibleDrawerSheetPreview() {
    DismissibleDrawerSheet {}
}
