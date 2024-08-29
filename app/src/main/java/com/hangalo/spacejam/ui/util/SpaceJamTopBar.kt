package com.hangalo.spacejam.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hangalo.spacejam.R

/**
 * TopAppBar for Home screen.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SpaceJamTopBar(
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
