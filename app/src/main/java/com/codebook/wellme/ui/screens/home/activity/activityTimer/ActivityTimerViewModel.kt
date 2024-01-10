package com.codebook.wellme.ui.screens.home.activity.activityTimer

import androidx.lifecycle.ViewModel
import com.codebook.wellme.ui.screens.home.activity.startingActivity.StartingActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


//sealed class StartingActivityStateUiEvents {
//    data class DurationSeconds(val value: Long) : StartingActivityStateUiEvents()
//    data class Type(val value: Int) : StartingActivityStateUiEvents()
//}

class ActivityTimerViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<StartingActivityState> =
        MutableStateFlow(StartingActivityState())
    var uiState: StateFlow<StartingActivityState> = _uiState

//    fun onEvent(events: StartingActivityStateUiEvents) {
//        when (events) {
//            is StartingActivityStateUiEvents.Type -> {
//                _uiState.value = uiState.value.copy(type = events.value)
//            }
//            else  -> {
//                _uiState.value = uiState.value.copy(duration = events.value)
//            }
//        }
//    }

    fun isValidScreen(): Boolean {
        val state = _uiState.value
        return state.type != -1
    }
}