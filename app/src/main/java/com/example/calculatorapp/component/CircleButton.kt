package com.example.calculatorapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.CalculatorViewModel
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import com.example.calculatorapp.ui.theme.LightNumberColor
import com.example.calculatorapp.ui.theme.LightWhiteButton

@Composable
fun CircleButton(
    background : Color,
    content: String,
    contentColor : Color,
    contentSize : TextUnit = 32.sp,
    onClick : () -> Unit = {}
) {
    val viewModel : CalculatorViewModel = viewModel()
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(68.dp)
            .aspectRatio(1f)
            .background(color = background)
            .clickable {
                viewModel.inputValidation()
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = content,
            color = contentColor,
            fontWeight = FontWeight.Normal,
            fontSize = contentSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CircleButtonPreview() {
    CalculatorAppTheme {
        CircleButton(background = LightWhiteButton, content = "7", contentColor = LightNumberColor)
    }
}