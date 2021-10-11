package com.example.healthcheck.ui.screens.list_screen


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthcheck.R
import com.example.healthcheck.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToBmiMeasurement: (BmiId: Int) -> Unit,
    navigateToHome: () -> Unit,
    viewModel: ListViewModel = hiltViewModel(),
    event: Event
) {


    LaunchedEffect(key1 = event) {
        viewModel.handleEvent(event = event)
    }


    val allBmiMeasurement by viewModel.allBmiMeasurement.collectAsState()
    val sortState by viewModel.sortState.collectAsState()
    val bmiSortedByDate by viewModel.bmiSortedByDate.collectAsState()
    val bmiSortedByIndex by viewModel.bmiSortedByIndex.collectAsState()
    val scaffoldState = rememberScaffoldState()
    Timber.d("$event")

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        onComplete = { viewModel.event.value = it },
        onUndoClicked = { viewModel.event.value = it },
        event = event
    )

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            ListAppBar(navigateToHome = navigateToHome, viewModel = viewModel)


        },

        content = {
            ListContent(
                measurements = allBmiMeasurement,
                navigateToBmiMeasurement = navigateToBmiMeasurement,
                onSwipeToDelete = { bmiMeasurement, event ->
                    viewModel.event.value = event
                    viewModel.updateBmiMeasurementFields(currentBmiMeasurement = bmiMeasurement)
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()


                },
                sortState = sortState,
                bmiSortedByDate = bmiSortedByDate,
                bmiSortedByIndex = bmiSortedByIndex

            )
        }
    )

}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    onComplete: (Event) -> Unit,
    onUndoClicked: (Event) -> Unit,
    event: Event

) {


    val message = setMessage(event = event)
    val label = setActionLabel(event = event)

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = event) {
        if (event != Event.NO_EVENT) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = label,
                    duration = SnackbarDuration.Short
                )
                undoDeleteTask(
                    event = event,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )

            }
            onComplete(Event.NO_EVENT)
        }

    }
}

private fun undoDeleteTask(
    event: Event,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Event) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && event == Event.DELETE
    ) {
        onUndoClicked(Event.UNDO)
    }
}
@Composable
private fun setActionLabel(event: Event): String {
    return if (event == Event.DELETE) "UNDO" else "OK"
}
@Composable
private fun setMessage(event: Event): String {
    return if (event == Event.DELETE) {
        stringResource(R.string.label_message_delete)
    } else {
        stringResource(R.string.label_message_restored)
    }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
@Preview
private fun ListScreenPreview() {
    val viewModel: ListViewModel = hiltViewModel()
    ListScreen(
        navigateToBmiMeasurement = {},
        navigateToHome = { },
        viewModel = viewModel,
        Event.NO_EVENT
    )
}


