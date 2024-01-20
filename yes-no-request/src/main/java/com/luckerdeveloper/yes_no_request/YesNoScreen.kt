package com.luckerdeveloper.yes_no_request

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun YesNoRoute(
    modifier: Modifier = Modifier,
    viewModel: YesNoViewModelImpl = hiltViewModel(),
    setSystemBarColors: (Color, Boolean) -> Unit,
) {
    val viewState: YesNoViewModel.ViewState by viewModel.viewState.collectAsStateWithLifecycle()
    YesNoScreen(
        viewState = viewState,
        onAskButtonClick = { viewModel.handleEvent(YesNoViewModel.Event.OnClickAsk) },
        setSystemBarColors = setSystemBarColors,
        modifier = modifier,
    )
}

@Composable
internal fun YesNoScreen(
    viewState: YesNoViewModel.ViewState,
    onAskButtonClick: () -> Unit,
    setSystemBarColors: (Color, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = getBackgroundColor(viewState)
    setSystemBarColors(backgroundColor, true)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
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

        if (viewState is YesNoViewModel.ViewState.Success && viewState.imageUrl != null) {
            GifImage(
                link = viewState.imageUrl,
                modifier = Modifier.align(Alignment.Center),
            )
        }

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

private fun getBackgroundColor(viewState: YesNoViewModel.ViewState): Color {
    return if (viewState is YesNoViewModel.ViewState.Success) {
        if (viewState.answer) {
            Color(0xFFAFFD93)
        } else {
            Color(0xFFFF8383)
        }
    } else {
        Color.Unspecified
    }
}

@Preview()
@Composable
private fun YesNoPreview() {
    YesNoScreen(
        viewState = YesNoViewModel.ViewState.Init,
        onAskButtonClick = {},
        setSystemBarColors = {_, _ -> },
    )
}
