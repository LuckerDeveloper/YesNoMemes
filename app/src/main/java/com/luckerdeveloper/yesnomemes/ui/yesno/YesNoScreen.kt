package com.luckerdeveloper.yesnomemes.ui.yesno

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luckerdeveloper.yesnomemes.ui.theme.AppTheme

@Composable
fun YesNoRoute(
    viewModel: YesNoViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState: YesNoViewModel.ViewState by viewModel.viewState.collectAsStateWithLifecycle()
    YesNoScreen(
        viewState = viewState,
        onAskButtonClick = { viewModel.handleEvent(YesNoViewModel.Event.OnClickAsk) },
        modifier = modifier,
    )
}

@Composable
fun YesNoScreen(
    viewState: YesNoViewModel.ViewState,
    onAskButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val text = when (viewState) {
            is YesNoViewModel.ViewState.Init -> "Yes or no?"
            is YesNoViewModel.ViewState.Loading -> "Loading"
            is YesNoViewModel.ViewState.Success -> viewState.answer.toString()
            is YesNoViewModel.ViewState.Error -> viewState.answer.toString()
        }
        Text(
            text = text,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 8.dp)
                .wrapContentSize(),
        )

        Button(
            onClick = onAskButtonClick,
            enabled = viewState is YesNoViewModel.ViewState.Init,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 8.dp)
                .wrapContentHeight(align = Alignment.Bottom)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Ask",
            )
        }
    }
}

@Preview()
@Composable
private fun AppPreview() {
    AppTheme {
        YesNoScreen(
            viewState = YesNoViewModel.ViewState.Init,
            onAskButtonClick = {}
        )
    }
}
