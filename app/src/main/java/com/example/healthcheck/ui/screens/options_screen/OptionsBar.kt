package com.example.healthcheck.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.R
import com.example.healthcheck.ui.theme.LARGE_SPACER
import com.example.healthcheck.ui.theme.SMALL_PADDING
import com.example.healthcheck.util.Event


@Composable
fun OptionsAppBar(
    onAboutClicked: () -> Unit,
    navigateToList: (Event) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            AboutAction(onAboutClicked = onAboutClicked)
        },


        title = {
                TitleText()
        },
        actions = {
            HistoryAction(navigateToList = navigateToList)
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
                    modifier = Modifier.padding(start = SMALL_PADDING),
                    text = stringResource(id = R.string.about_app),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
fun HistoryAction(navigateToList: (Event) -> Unit) {
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
                navigateToList(Event.NO_EVENT)
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
fun TitleText(){
    Spacer(modifier = Modifier.width(LARGE_SPACER))
    Text(text = stringResource(id = R.string.choose_options))
}

@Composable
@Preview
private fun AppBarPreview() {
    OptionsAppBar({}) {}

}
        