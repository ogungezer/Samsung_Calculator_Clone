package com.example.calculatorapp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.CalculatorUiState
import com.example.calculatorapp.ui.theme.LightNumberColor
import com.example.calculatorapp.ui.theme.LightOperationColor

@Composable
fun CustomText(state: CalculatorUiState){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 50.dp)
    ){
        Text(
            text = buildAnnotatedString
            {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary)){
                    append(text = state.numberOne.replace(".",","))
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground, letterSpacing = 0.08.em, fontWeight = FontWeight.Light, fontSize = 35.sp, baselineShift = BaselineShift(0.12f))){
                    append(text = state.operation?.symbol ?: "")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary)){
                    append(text = state.numberTwo.replace(".",","))
                }
            },
            maxLines = 2,
            letterSpacing = 0.01.em,
            style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight(350), textAlign = TextAlign.End),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
        )
    }

}
