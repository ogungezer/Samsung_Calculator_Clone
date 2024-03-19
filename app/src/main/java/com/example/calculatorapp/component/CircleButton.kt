package com.example.calculatorapp.component

import android.util.Size
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import com.example.calculatorapp.ui.theme.LightNumberColor
import com.example.calculatorapp.ui.theme.LightWhiteButton
import kotlinx.coroutines.delay
import org.w3c.dom.Text

@Composable
fun CircleButton(
    background : Color,
    content: String,
    contentColor : Color,
    contentSize : TextUnit = 32.sp,
    onClick : () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(68.dp)
            .aspectRatio(1f)
            .background(color = background)
            .clickable {
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