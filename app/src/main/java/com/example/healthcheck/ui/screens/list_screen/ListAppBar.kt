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
import com.example.healthcheck.components.DisplayAlertDialog
import com.example.healthcheck.ui.theme.SMALL_PADDING
import com.example.healthcheck.util.Event

@Composable
fun ListAppBar(navigateToHome: () -> Unit, viewModel: ListViewModel) {
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
            DeleteAllAlertDialog (onDeleteAllConfirmed = {
                viewModel.deleteAllBmiMeasurements()
            })
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
fun DeleteAllAlertDialog(onDeleteAllConfirmed: () -> Unit){

    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(id = R.string.clear_history),
        message = stringResource(id = R.string.delete_all_confirmation),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = {
            onDeleteAllConfirmed()
        }
    )
    DeleteAllAction(onDeleteAllClicked = {openDialog = true})



}

@Composable
@Preview
private fun ListAppBarPreview() {
   ListAppBarPreview()
}