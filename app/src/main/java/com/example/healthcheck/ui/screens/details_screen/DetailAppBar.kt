package com.example.healthcheck.ui.screens.details_screen

import com.example.healthcheck.ui.screens.list_screen.ListViewModel


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthcheck.R
import com.example.healthcheck.components.DisplayAlertDialog
import com.example.healthcheck.components.SortByBmiItem
import com.example.healthcheck.components.SortByDateItem
import com.example.healthcheck.components.SortByNoPriorityItem
import com.example.healthcheck.util.SortOrder

@Composable
fun DetailAppBar(
    navigateToList: () -> Unit,
    navigateToHome: () -> Unit,
    onShareClicked: () -> Unit,
    viewModel: DetailsViewModel
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigateToList() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.back_icon)
                )
            }
        },
        title = { Text(text = stringResource(id = R.string.details_app_bar_title)) },
        actions = {
            HomeAction {
                navigateToHome()
            }
            ShareAction {
                onShareClicked()

            }


        }

    )

}

@Composable
fun HomeAction(navigateToHome: () -> Unit) {

    IconButton(onClick = { navigateToHome() }) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = stringResource(id = R.string.home_icon)
        )
    }

}

@Composable
fun ShareAction(onShareClicked: () -> Unit) {

    IconButton(onClick = { onShareClicked() }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = stringResource(id = R.string.share_icon)
        )
    }

}


@Composable
@Preview
private fun DetailsAppBarPreview() {
    val viewModel: DetailsViewModel = hiltViewModel()
    DetailAppBar({}, {}, {}, viewModel = viewModel)
}
