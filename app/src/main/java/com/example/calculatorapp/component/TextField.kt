package com.example.calculatorapp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.presentation.CalculatorUiState
import java.util.Locale

@Composable
fun CustomText(state: CalculatorUiState){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 50.dp, start = 20.dp)
    ){
        val textSize = if(state.numberOne.length + state.numberTwo.length > 12)  24.sp
            else if(state.numberTwo.length > 10)  24.sp
                else if(state.numberOne.length > 10)  24.sp
                    else 34.sp

        val numberOne = if(state.numberOne.isNotBlank()){
            String.format(Locale.GERMANY, "%,d", state.numberOne.split(',').getOrElse(0){""}.toBigIntegerOrNull()) + (if(state.numberOne.contains(",")) "," else "") +
             state.numberOne.split(',').getOrElse(1){""}
        } else {
            state.numberOne
        }
        val numberTwo = if(state.numberTwo.isNotBlank()){
            String.format(Locale.GERMANY, "%,d", state.numberTwo.split(',').getOrElse(0){""}.toBigIntegerOrNull()) + (if(state.numberTwo.contains(",")) "," else "") +
                    state.numberTwo.split(',').getOrElse(1){""}
        } else {
            state.numberTwo
        }

        Text(
            text = buildAnnotatedString
            {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary)){
                    append(text = numberOne)
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground, letterSpacing = 0.08.em, fontWeight = FontWeight.Light, fontSize = (textSize.value-10f).sp, baselineShift = BaselineShift(0.12f))){
                    append(text = state.operation?.symbol ?: "")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary)){
                    append(text = numberTwo)
                }
            },
            maxLines = 2,
            letterSpacing = 0.01.em,
            style = TextStyle(fontSize = textSize, fontWeight = FontWeight(350), textAlign = TextAlign.End),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
        )
    }

}