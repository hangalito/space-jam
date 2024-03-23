package com.hangalo.spacejam.ui.screens.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissibleDrawerSheet
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


// TODO: remove the properties initializers in production.
data class MenuSheetActions(
    val onHomeClick: () -> Unit = {},
    val onYesterdayClick: () -> Unit = {},
    val on2daysClick: () -> Unit = {},
    val onSelectDateClick: () -> Unit = {},
    val onSelectDateRangeClick: () -> Unit = {},
    val onSeeSavedClick: () -> Unit = {},
    val onUploadClick: () -> Unit = {},
    val onSignInClick: () -> Unit = {},
)

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
            label = { Text(stringResource(id = R.string.home)) },
            selected = false,
            onClick = { actions.onHomeClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_today),
                    contentDescription = stringResource(id = R.string.home)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.see_yesterday_pic)) },
            selected = false,
            onClick = { actions.onYesterdayClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(id = R.string.see_yesterday_pic)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.see_2d_pic)) },
            selected = false,
            onClick = { actions.on2daysClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(id = R.string.see_2d_pic)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.select_date)) },
            selected = false,
            onClick = { actions.onSelectDateClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_select_date),
                    contentDescription = stringResource(id = R.string.select_date)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.select_date_range)) },
            selected = false,
            onClick = { actions.onSelectDateRangeClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_date_range),
                    contentDescription = stringResource(id = R.string.select_date_range)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.see_saved)) },
            selected = false,
            onClick = { actions.onSeeSavedClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_saved),
                    contentDescription = stringResource(id = R.string.see_saved)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.save_to_cloud)) },
            selected = false,
            onClick = { actions.onUploadClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_upload_data),
                    contentDescription = stringResource(id = R.string.save_to_cloud)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.sign_in)) },
            selected = false,
            onClick = { actions.onSignInClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_account),
                    contentDescription = stringResource(id = R.string.sign_in)
                )
            }
        )
    }
}

@Preview
@Composable
private fun MenuSheetPreview() {
    val mockData = MenuSheetActions()
    MenuSheet(actions = mockData)
}
