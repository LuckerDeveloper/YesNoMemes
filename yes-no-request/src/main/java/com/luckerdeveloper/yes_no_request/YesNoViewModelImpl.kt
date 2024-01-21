package com.luckerdeveloper.yes_no_request

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luckerdeveloper.network.network.YesNoService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

@HiltViewModel
class YesNoViewModelImpl @Inject constructor(private val yesNoService: YesNoService) : ViewModel(),
    YesNoViewModel {

    private val _viewState: MutableStateFlow<YesNoViewModel.ViewState> =
        MutableStateFlow(YesNoViewModel.ViewState.Init)
    override val viewState: StateFlow<YesNoViewModel.ViewState> = _viewState

    override val effect: Flow<YesNoViewModel.Effect> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )

    override fun handleEvent(event: YesNoViewModel.Event) {
        when (event) {
            YesNoViewModel.Event.OnClickAsk -> {
                viewModelScope.launch {
                    val deferredResult = async {
                        withTimeoutOrNull(3_000) {
                            return@withTimeoutOrNull load()
                        }
                    }
                    _viewState.value = YesNoViewModel.ViewState.Loading(3)
                    delay(1_000L)
                    _viewState.value = YesNoViewModel.ViewState.Loading(2)
                    delay(1_000L)
                    _viewState.value = YesNoViewModel.ViewState.Loading(1)
                    delay(1_000L)
                    val result = deferredResult.await()!!
                    _viewState.value = YesNoViewModel.ViewState.Success(
                        answer = result.first == "yes",
                        imageUrl = result.second,
                    )
                }
            }
        }
    }

    private suspend fun load(): Pair<String, String> {
        val response = yesNoService.getYesNo()
        Log.i(NETWORK_TAG, "response: $response")
        return Pair(response.answer, response.image)
    }

    companion object {
        const val NETWORK_TAG = "NETWORK_TAG"
    }
}
