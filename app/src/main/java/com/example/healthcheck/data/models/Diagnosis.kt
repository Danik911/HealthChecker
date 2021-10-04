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

enum class Diagnosis(val color: Color, val angle: Float) {
    SevereUnderWeight(color = Color.Red, angle = 0.2f),
    UnderWeight(color = Color.Yellow, angle = 0.3f),
    NormalWeight(color = Color.Green, angle = 0.4f),
    OverWeight(color = Color.Yellow, angle = 0.5f),
    ObesityFirstStage(color = Color.Red, angle = 0.6f),
    ObesitySecondStage(color = Color.Red, angle = 0.7f),
    ObesityThirdStage(color = Color.Red, angle = 0.9f)


}