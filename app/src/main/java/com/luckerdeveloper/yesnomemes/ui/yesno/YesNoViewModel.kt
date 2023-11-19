package com.luckerdeveloper.yesnomemes.ui.yesno

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface YesNoViewModel {

    val viewState: StateFlow<ViewState>
    val effect: Flow<Effect>

    fun handleEvent(event: Event)

    sealed class Event {
        object OnClickAsk: Event()
    }

    sealed class ViewState {
        object Init: ViewState()
        object Loading: ViewState()
        data class Success(val answer: Boolean, val imageUrl: String?): ViewState()
        data class Error(val throwable: Throwable, val answer: Boolean, val imageUrl: String? = null): ViewState()
    }

    sealed class Effect {
        class ShowToast(text: String): Effect()
    }
}
