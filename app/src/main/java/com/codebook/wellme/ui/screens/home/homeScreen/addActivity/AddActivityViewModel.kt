package com.codebook.wellme.ui.screens.home.homeScreen.addActivity

import androidx.lifecycle.ViewModel
import com.codebook.wellme.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ActivityState(val id: Int = 0, val icon: Int = 0, val name: String = "")
object Activities {
    val activitiesList = listOf(
        ActivityState(0, R.drawable.running, "Running"),
        ActivityState(1, R.drawable.walking, "Walking"),
        ActivityState(2, R.drawable.fitness, "Fitness"),
        ActivityState(3, R.drawable.rollers, "Rollers"),
        ActivityState(4, R.drawable.ic_yoga, "Yoga"),
        ActivityState(5, R.drawable.breath, "Breathing"),
        ActivityState(6, R.drawable.biking, "Biking")
    )
}

data class AddActivityState(
    var type: Int = -1,
    var timeOfDay: Int = -1,
    var durationMins: Int? = null,
    var durationSeconds: Int? = null,
) {

}

sealed class AddActivityStateUiEvents {
    data class DurationMins(val value: Int) : AddActivityStateUiEvents()
    data class DurationSeconds(val value: Int) : AddActivityStateUiEvents()
    data class Type(val value: Int) : AddActivityStateUiEvents()
    data class TimeOfDay(val value: Int) : AddActivityStateUiEvents()
}

class AddActivityViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<AddActivityState> =
        MutableStateFlow(AddActivityState())
    var uiState: StateFlow<AddActivityState> = _uiState

    fun onEvent(events: AddActivityStateUiEvents) {
        when (events) {
            is AddActivityStateUiEvents.Type -> {
                _uiState.value = uiState.value.copy(type = events.value)
            }

            is AddActivityStateUiEvents.TimeOfDay -> {
                _uiState.value = uiState.value.copy(timeOfDay = events.value)

            }

            is AddActivityStateUiEvents.DurationMins -> {
                _uiState.value = uiState.value.copy(durationMins = events.value)

            }

            is AddActivityStateUiEvents.DurationSeconds -> {
                _uiState.value = uiState.value.copy(durationSeconds = events.value)
            }

        }
    }

    fun isValidScreen(): Boolean {
        val state = _uiState.value
        return state.type != -1 &&
                state.timeOfDay != -1 &&
                state.durationMins != null &&
                state.durationSeconds != null &&
                (state.durationSeconds != 0 || state.durationMins != 0)
    }
}