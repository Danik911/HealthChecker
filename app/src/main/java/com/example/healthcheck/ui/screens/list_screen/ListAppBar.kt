package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.R
import com.example.healthcheck.ui.theme.SMALL_PADDING

@Composable
fun ListAppBar(navigateToHome: () -> Unit, navigateToList: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigateToHome() }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.home_icon)
                )
            }
        },
        title = { Text(text = stringResource(id = R.string.list_app_bar_title)) },
        actions = {
            DeleteAllAction {
                navigateToList()
            }
        }

    )

}

@Composable
fun DeleteAllAction(onDeleteAllClicked: () -> Unit) {

    IconButton(onClick = { onDeleteAllClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_all_icon)
        )
    }
}
@Composable
@Preview
private fun ListAppBarPreview() {
   ListAppBarPreview()
}