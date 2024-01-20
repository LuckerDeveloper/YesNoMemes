package com.luckerdeveloper.yes_no_request

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luckerdeveloper.network.network.YesNoApiResponse
import com.luckerdeveloper.network.network.YesNoService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
                    _viewState.value = YesNoViewModel.ViewState.Loading
                    val result = load()
                    _viewState.value = YesNoViewModel.ViewState.Success(
                        answer = result.first == "yes",
                        imageUrl = result.second,
                    )
                }
            }
        }
    }

    private suspend fun load(): Pair<String, String?> {
        val response = try {
            yesNoService.getYesNo()
        } catch (e: Exception) {
            Log.e(NETWORK_TAG, "load answer exception: $e")
            YesNoApiResponse("true")
        }
        Log.i(NETWORK_TAG, "response: $response")
        return Pair(response.answer, response.image)
    }

    companion object {
        const val NETWORK_TAG = "NETWORK_TAG"
    }
}
