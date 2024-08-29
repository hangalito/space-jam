package com.hangalo.spacejam.ui.util

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hangalo.spacejam.R


@Composable
fun MenuSheet(
    modifier: Modifier = Modifier,
    actions: MenuSheetActions,
) {
    DismissibleDrawerSheet(modifier) {
        Text(
            text = stringResource(R.string.app_name),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp)
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_today)) },
            selected = false,
            onClick = actions::onHomeClick,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_today),
                    contentDescription = stringResource(id = R.string.menu_today)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_yesterday)) },
            selected = false,
            onClick = actions::onYesterdayClick,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_yesterday),
                    contentDescription = stringResource(id = R.string.menu_yesterday),
                    modifier = Modifier.size(Icons.defaultIconSize())
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_choose_day)) },
            selected = false,
            onClick = actions::onSelectDateClick,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_select_date),
                    contentDescription = stringResource(id = R.string.menu_choose_day)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_date_range)) },
            selected = false,
            onClick = actions::onSelectDateRangeClick,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_date_range),
                    contentDescription = stringResource(id = R.string.menu_date_range),
                )
            }
        )
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_see_saved)) },
            selected = false,
            onClick = actions::onSeeSavedClick,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_saved),
                    contentDescription = stringResource(id = R.string.menu_see_saved)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_upload)) },
            selected = false,
            onClick = { actions.onUploadClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_upload_data),
                    contentDescription = stringResource(id = R.string.menu_upload)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.menu_sign_in)) },
            selected = false,
            onClick = actions::onSignInClick,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_account),
                    contentDescription = stringResource(id = R.string.menu_sign_in),
                )
            }
        )
    }
}

@Preview
@Composable
private fun MenuSheetPreview() {
    val mockData = object : MenuSheetActions {
        override fun onHomeClick() {}

        override fun onYesterdayClick() {}

        override fun onSelectDateClick() {}

        override fun onSelectDateRangeClick() {}

        override fun onSeeSavedClick() {}

        override fun onUploadClick() {}

        override fun onSignInClick() {}
    }
    MenuSheet(actions = mockData)
}
