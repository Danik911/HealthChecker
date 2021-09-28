package com.example.healthcheck.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.R


@Composable
fun AppBar(
    onAboutClicked: () -> Unit,
    onHistoryClicked: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            AboutAction(onAboutClicked = onAboutClicked)
        },
        title = {
            Text(
                text = stringResource(id = R.string.choose_options)

            )
        },
        actions = {
            HistoryAction(onHistoryClicked = onHistoryClicked)
        }
    )
}

@Composable
fun AboutAction(
    onAboutClicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {

        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = stringResource(id = R.string.about_icon)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onAboutClicked()
            }
            ) {

                Text(
                    text = stringResource(id = R.string.about_app),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
fun HistoryAction(onHistoryClicked: () -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {

        Icon(
            painterResource(id = R.drawable.vertical_icon),
            contentDescription = stringResource(id = R.string.history_icon)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onHistoryClicked()
            }
            ) {

                Text(
                    text = stringResource(id = R.string.history_drop_down),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
@Preview
private fun AppBarPreview() {
    AppBar({}, {})

}
        