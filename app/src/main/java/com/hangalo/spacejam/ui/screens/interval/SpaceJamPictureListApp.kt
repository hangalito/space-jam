package com.hangalo.spacejam.ui.screens.interval

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hangalo.spacejam.domain.ViewModelProvider.Factory
import com.hangalo.spacejam.ui.screens.interval.IntervalViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceJamPictureListApp(
    intervalViewModel: IntervalViewModel = viewModel(factory = Factory),
) {
    LargeTopAppBar(title = { /*TODO*/ })
}
