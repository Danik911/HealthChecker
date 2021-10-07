package com.example.healthcheck.data.models

import androidx.compose.ui.graphics.Color

/*sealed class BmiMassResult(val color: Color, val angle: Float) {
    object SevereUnderWeight : BmiMassResult(color = Color.Red, angle = 0.2f)
    object UnderWeight : BmiMassResult(color = Color.Yellow, angle = 0.3f)
    object NormalWeight : BmiMassResult(color = Color.Green, angle = 0.4f)
    object OverWeight : BmiMassResult(color = Color.Yellow, angle = 0.5f)
    object ObesityFirstStage : BmiMassResult(color = Color.Red, angle = 0.6f)
    object ObesitySecondStage : BmiMassResult(color = Color.Red, angle = 0.7f)
    object ObesityThirdStage : BmiMassResult(color = Color.Red, angle = 0.9f)


}*/

enum class Diagnosis(val color: Color, val angle: Float, val description: String) {
    SevereUnderWeight(
        color = Color.Red,
        angle = 0.2f,
        description = "Your suffer from severe underweight"
    ),
    UnderWeight(
        color = Color.Yellow,
        angle = 0.3f,
        description = "Your weight is lover than norm"
    ),
    NormalWeight(
        color = Color.Green,
        angle = 0.4f,
        description = "Your weight is normal"
    ),
    OverWeight(
        color = Color.Yellow,
        angle = 0.5f,
        description = "Your weight is higher than norm"
    ),
    ObesityFirstStage(
        color = Color.Red,
        angle = 0.6f,
        description = "You suffer from first stage of obesity"
    ),
    ObesitySecondStage(
        color = Color.Red,
        angle = 0.7f,
        description = "You suffer from second stage of obesity"
    ),
    ObesityThirdStage(
        color = Color.Red,
        angle = 0.9f,
        description = "You suffer from third stage of obesity"
    )


}