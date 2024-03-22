package com.example.calculatorapp.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.CalculatorUiState
import com.example.calculatorapp.CalculatorViewModel
import com.example.calculatorapp.OperationEvent
import com.example.calculatorapp.R
import com.example.calculatorapp.ui.theme.LightBackspaceColor
import com.example.calculatorapp.ui.theme.LightDisabledBackspaceColor

@Composable
fun IconRow(
    iconSize: Dp = 26.dp,
    onEvent: (OperationEvent) -> Unit = {},
    state : CalculatorUiState
){
    val viewModel : CalculatorViewModel = viewModel()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(2f)
        ) {
            Icon(
                painter = painterResource(R.drawable.gecmis),
                contentDescription = "history",
                tint = if(state.historyOperationList.isNotEmpty()) MaterialTheme.colorScheme.surfaceTint else (MaterialTheme.colorScheme.surfaceTint).copy(alpha = 0.3f),
                modifier = Modifier
                    .size(iconSize)
                    .clickable(enabled = state.historyOperationList.isNotEmpty()) {
                        viewModel.updateTabState()
                        onEvent(OperationEvent.OpenHistory)
                    }
            )
            Icon(
                painter = painterResource(R.drawable.cetvel),
                contentDescription = "ruler",
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
                    .size(iconSize)
                    .clickable { /*TODO*/ }
            )
            Icon(
                painter = painterResource(R.drawable.islem),
                contentDescription = "other operations",
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
                    .size(iconSize)
                    .clickable { /*TODO*/ }
            )
        }
        Box(modifier = Modifier.weight(0.3f), contentAlignment = Alignment.Center){
            IconButton(
                onClick ={
                        viewModel.inputValidation()
                        onEvent(OperationEvent.Delete)
                    },
                enabled = state.numberOne.isNotBlank(),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface
                )
            ){
                Icon(
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = "backspace",
                    modifier = Modifier.size(iconSize)
                )
            }
        }

    }
}
