package com.example.calculatorapp.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.CalculatorViewModel
import com.example.calculatorapp.OperationEvent
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

@Composable
fun HistoryList(onEvent : (OperationEvent) -> Unit) {
    val viewModel : CalculatorViewModel = viewModel()
    val state = viewModel.uiState.collectAsState()
    println("list : ${state.value.historyOperationList}")



    Row(modifier = Modifier.fillMaxWidth(0.75f)){
        Box(modifier = Modifier
            //.fillMaxWidth(0.75f)
            .background(MaterialTheme.colorScheme.background)
            .weight(1f) //sil
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 4.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.Bottom,
                ){
                    items(state.value.historyOperationList){
                        if(it.num1.isNotBlank() && it.num2.isNotBlank() && it.operation.isNotBlank()){
                            Column(horizontalAlignment = Alignment.End ,modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)) {
                                Text(
                                    text = it.num1 + it.operation + it.num2,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    textAlign = TextAlign.End,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "=${it.result}",
                                    color = MaterialTheme.colorScheme.onBackground,
                                    textAlign = TextAlign.End,
                                    fontSize = 22.sp
                                )
                            }
                        }
                    }
                }
                Button(onClick = { onEvent(OperationEvent.ClearHistory) }, modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ){
                    Text(
                        text = "Geçmişi Temizle",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

            }
        }

        Spacer(
            modifier = Modifier
                .width(0.2.dp)
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.scrim)
        )

    }


}

