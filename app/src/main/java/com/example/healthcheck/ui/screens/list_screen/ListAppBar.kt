package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
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
fun ListAppBar(
    navigateToHome: () -> Unit,
    viewModel: ListViewModel
) {
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
            SortAction(onSortClicked = { viewModel.persistSortState(it) } )
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
fun SortAction(onSortClicked: (SortOrder) -> Unit){
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true}) {
        Icon(painter = painterResource(
            id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_icon)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(SortOrder.BY_BMI)
            }) {

                SortByBmiItem()
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(SortOrder.BY_DATE)
            }) {

                SortByDateItem()
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(SortOrder.NONE)
            }) {

                SortByNoPriorityItem()

            }

        }
    }

}

@Composable
@Preview
private fun ListAppBarPreview() {
    val viewModel: ListViewModel = hiltViewModel()
    ListAppBar({},viewModel = viewModel)
}