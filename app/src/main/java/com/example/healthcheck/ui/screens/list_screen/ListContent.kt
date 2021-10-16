package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.healthcheck.R
import com.example.healthcheck.components.BmiContent
import com.example.healthcheck.components.BmiElement
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.ui.theme.MEDIUM_PADDING
import com.example.healthcheck.util.Event
import com.example.healthcheck.util.RequestState
import com.example.healthcheck.util.SortOrder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ListContent(
    measurements: RequestState<List<BmiMeasurement>>,
    navigateToBmiMeasurement: (bmiId: Int) -> Unit,
    onSwipeToDelete: (BmiMeasurement, Event) -> Unit,
    sortState: RequestState<SortOrder>,
    bmiSortedByDate: List<BmiMeasurement>,
    bmiSortedByIndex: List<BmiMeasurement>

) {

    if (sortState is RequestState.Success) {
        when (sortState.data) {
            SortOrder.NONE -> {
                if (measurements is RequestState.Success) {
                    DisplayMeasurements(
                        measurements = measurements.data,
                        navigateToBmiMeasurement = navigateToBmiMeasurement,
                        onSwipeToDelete = onSwipeToDelete
                    )
                }
            }
            SortOrder.BY_DATE -> {

                DisplayMeasurements(
                    measurements = bmiSortedByDate,
                    navigateToBmiMeasurement = navigateToBmiMeasurement,
                    onSwipeToDelete = onSwipeToDelete
                )


            }
            SortOrder.BY_BMI -> {
                DisplayMeasurements(
                    measurements = bmiSortedByIndex,
                    navigateToBmiMeasurement = navigateToBmiMeasurement,
                    onSwipeToDelete = onSwipeToDelete
                )
            }
        }

    }

}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DisplayMeasurements(
    measurements: List<BmiMeasurement>,
    navigateToBmiMeasurement: (bmiId: Int) -> Unit,
    onSwipeToDelete: (BmiMeasurement, Event) -> Unit
) {
    if (measurements.isEmpty()) {
        EmptyListContent()
    } else {
        LazyColumn {
            items(
                items = measurements,
                key = { item: BmiMeasurement ->
                    item.bmiId
                }
            ) { item ->
                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                    val scope = rememberCoroutineScope()
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(item, Event.DELETE)

                    }
                }
                val degrees by animateFloatAsState(
                    if (dismissState.targetValue == DismissValue.Default) 0f
                    else -45f
                )
                var itemAppeared by remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(key1 = true) {
                    itemAppeared = true
                }
                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ) {
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                        background = { DismissBackground(degrees = degrees) },
                        dismissContent = {
                            BmiElement(
                                bmiMeasurement = item,
                                bmiContent = { BmiContent(bmiMeasurement = item) }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DismissBackground(degrees: Float) {

    Box(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier
                .rotate(degrees = degrees)
                .padding(end = MEDIUM_PADDING),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_rotated_icon),
            tint = Color.White
        )

    }

}

/*
@ExperimentalMaterialApi
@Composable
@Preview
fun ListContentPreview() {
    val viewModel: ListViewModel = hiltViewModel()
    ListContent(
        measurements = listOf<BmiMeasurement>(
            BmiMeasurement(
                0, 0l, Diagnosis.NormalWeight, 0f
            )
        ),
        navigateToBmiMeasurement = {},
        onSwipeToDelete = { */
/*TODO*//*
 },
        viewModel = viewModel
    )

}*/
