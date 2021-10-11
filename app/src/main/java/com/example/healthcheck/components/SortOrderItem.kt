package com.example.healthcheck.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.R
import com.example.healthcheck.ui.theme.LARGE_PADDING
import com.example.healthcheck.util.SortOrder

@Composable
fun PriorityItem(
    sortOrder: SortOrder,
    icon: @Composable () -> Unit,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Text(
            modifier = Modifier
                .padding(start = LARGE_PADDING),
            text = text,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )
    }

}

@Composable
fun SortByDateItem() {
    PriorityItem(
        sortOrder = SortOrder.BY_DATE, icon = {
            Icon(
                imageVector = Icons.Filled.Timer,
                contentDescription = stringResource(id = R.string.timer_icon)
            )
        },
        text = stringResource(id = R.string.sort_by_date)
    )
}
@Composable
fun SortByBmiItem() {
    PriorityItem(
        sortOrder = SortOrder.BY_BMI, icon = {
            Icon(
                imageVector = Icons.Filled.Adjust,
                contentDescription = stringResource(id = R.string.sot_by_bmi_icon)
            )
        },
        text = stringResource(id = R.string.sort_by_bmi)
    )
}
@Composable
fun SortByNoPriorityItem() {
    PriorityItem(
        sortOrder = SortOrder.BY_DATE, icon = {
            Icon(
                imageVector = Icons.Filled.RadioButtonUnchecked,
                contentDescription = stringResource(id = R.string.sot_by_no_priority_icon)
            )
        },
        text = stringResource(id = R.string.sort_by_no_priority)
    )
}


@Composable
@Preview
fun PriorityItemPreview() {
    SortByDateItem()
}