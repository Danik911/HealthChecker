package com.example.healthcheck.ui.screens.result_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.R
import com.example.healthcheck.ui.screens.param_screen.ParamScreen
import com.example.healthcheck.ui.screens.param_screen.ParamViewModel
import com.example.healthcheck.ui.theme.SMALL_PADDING


@Composable
fun ResultAppBar(navigateToHome: () -> Unit, navigateToList: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigateToHome() }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.home_icon)
                )
            }
        },
        title = { Text(text = stringResource(id = R.string.result_app_bar)) },
        actions = {
            HistoryAction {
               navigateToList()
            }
        }

    )

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
                    modifier = Modifier.padding(start = SMALL_PADDING),
                    text = stringResource(id = R.string.history_drop_down),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}
@Composable
@Preview
private fun ResultAppBarPreview() {
    ResultAppBar ({},{})
}