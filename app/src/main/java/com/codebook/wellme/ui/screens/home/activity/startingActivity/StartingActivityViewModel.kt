package com.codebook.wellme.ui.screens.home.activity.startingActivity

import androidx.lifecycle.ViewModel
import com.codebook.wellme.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID


data class StartingActivityState(
    var id: UUID = UUID.randomUUID(),
    var type: Int = -1,
    var duration: Long = 0,
)

sealed class StartingActivityStateUiEvents {
    data class DurationSeconds(val value: Long) : StartingActivityStateUiEvents()
    data class Type(val value: Int) : StartingActivityStateUiEvents()
}

class StartingActivityViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<StartingActivityState> =
        MutableStateFlow(StartingActivityState())
    var uiState: StateFlow<StartingActivityState> = _uiState

    fun onEvent(events: StartingActivityStateUiEvents) {
        when (events) {
            is StartingActivityStateUiEvents.Type -> {
                _uiState.value = uiState.value.copy(type = events.value)
            }
            is StartingActivityStateUiEvents.DurationSeconds -> {
                _uiState.value = uiState.value.copy(duration = events.value)
            }
        }
    }

    fun isValidScreen(): Boolean {
        val state = _uiState.value
        return state.type != -1
    }
}