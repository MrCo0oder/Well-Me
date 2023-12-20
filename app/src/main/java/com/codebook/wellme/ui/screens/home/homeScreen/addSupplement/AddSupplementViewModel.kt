package com.codebook.wellme.ui.screens.home.homeScreen.addSupplement

import androidx.lifecycle.ViewModel
import com.codebook.wellme.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime
import java.util.Date

enum class Forms {
    Pill,
    Tablet,
    Sachet,
    Drops,
    Spoon
}

enum class TimeOfDay {
    Morning,
    Afternoon,
    Evening,
    Night
}

data class SupFormsState(val id: Int = 0, val icon: Int = 0, val name: String = "")
data class TimeOfDayState(
    val id: Int = 0,
    val icon: Int = 0,
    val name: String = "",
    val time: LocalTime? = null
)


object Supplements {
    val formList = listOf(
        SupFormsState(0, R.drawable.pill, Forms.Pill.name),
        SupFormsState(1, R.drawable.tablet, Forms.Tablet.name),
        SupFormsState(2, R.drawable.sashet, Forms.Sachet.name),
        SupFormsState(3, R.drawable.drops, Forms.Drops.name),
        SupFormsState(4, R.drawable.spoon, Forms.Spoon.name),
    )

    val timeOfDayList = listOf(
        TimeOfDayState(0, R.drawable.sunrise, TimeOfDay.Morning.name, LocalTime.of(10, 0, 0)),
        TimeOfDayState(1, R.drawable.afternoon, TimeOfDay.Afternoon.name, LocalTime.of(15, 37, 0)),
        TimeOfDayState(2, R.drawable.sunset, TimeOfDay.Evening.name, LocalTime.of(17, 40, 0)),
        TimeOfDayState(3, R.drawable.night, TimeOfDay.Night.name, LocalTime.of(21, 0, 0)),
    )
}

data class AddSuppState(
    var name: String = "",
    var form: Int = -1,
    var dosage: Int = -1,
    var frequency: Int = 0,
    var timeOfDay: Int = -1,
    var takingWithMeals: Int = -1,
    var startDate: Date? = null,
    var endDate: Date? = null,
)


sealed class AddSuppStateUiEvents {
    data class Name(val value: String) : AddSuppStateUiEvents()
    data class StartDate(val value: Date) : AddSuppStateUiEvents()
    data class EndDate(val value: Date) : AddSuppStateUiEvents()
    data class Form(val value: Int) : AddSuppStateUiEvents()
    data class Dosage(val value: Int) : AddSuppStateUiEvents()
    data class Frequency(val value: Int) : AddSuppStateUiEvents()
    data class TimeOfDay(val value: Int) : AddSuppStateUiEvents()
    data class TakingWithMeals(val value: Int) : AddSuppStateUiEvents()
}

class AddSupplementViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<AddSuppState> =
        MutableStateFlow(AddSuppState())
    var uiState: StateFlow<AddSuppState> = _uiState

    fun onEvent(events: AddSuppStateUiEvents) {
        when (events) {
            is AddSuppStateUiEvents.Name -> {
                _uiState.value = uiState.value.copy(name = events.value)
            }

            is AddSuppStateUiEvents.Form -> {
                _uiState.value = uiState.value.copy(form = events.value)
            }

            is AddSuppStateUiEvents.Frequency -> {
                _uiState.value = uiState.value.copy(frequency = events.value)
            }

            is AddSuppStateUiEvents.Dosage -> {
                _uiState.value = uiState.value.copy(dosage = events.value)
            }

            is AddSuppStateUiEvents.TakingWithMeals -> {
                _uiState.value = uiState.value.copy(takingWithMeals = events.value)
            }

            is AddSuppStateUiEvents.TimeOfDay -> {
                _uiState.value = uiState.value.copy(timeOfDay = events.value)
            }

            is AddSuppStateUiEvents.StartDate -> {
                _uiState.value = uiState.value.copy(startDate = events.value)
            }

            is AddSuppStateUiEvents.EndDate -> {
                _uiState.value = uiState.value.copy(endDate = events.value)
            }
        }
    }

    fun isValidSupp(): Boolean {
        val state = _uiState.value
        return state.name.length >= 3 &&
                state.form != -1 &&
                state.dosage != -1 &&
                state.frequency != -1 &&
                state.takingWithMeals != -1 &&
                state.timeOfDay != -1 &&
                state.startDate != null &&
                state.endDate != null
    }
}