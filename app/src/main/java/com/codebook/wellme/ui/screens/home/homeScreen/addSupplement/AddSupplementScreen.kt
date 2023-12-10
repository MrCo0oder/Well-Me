package com.codebook.wellme.ui.screens.home.homeScreen.addSupplement

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.NotificationService
import com.codebook.wellme.R
import com.codebook.wellme.navigation.Screen
import com.codebook.wellme.ui.BodyText3Text
import com.codebook.wellme.ui.BodyTextTwo
import com.codebook.wellme.ui.CustomDropDown
import com.codebook.wellme.ui.DateRangePickerSample
import com.codebook.wellme.ui.FormsSelectableCard
import com.codebook.wellme.ui.HDivider
import com.codebook.wellme.ui.Headline3
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SelectableCard
import com.codebook.wellme.ui.SquareButton
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.getFormattedDate
import com.codebook.wellme.ui.screens.home.homeScreen.addSupplement.Supplements.formList
import com.codebook.wellme.ui.screens.home.homeScreen.addSupplement.Supplements.timeOfDayList
import com.codebook.wellme.ui.theme.DarkGrey
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.LilacPetals
import com.codebook.wellme.ui.theme.LilacPetalsDark
import com.codebook.wellme.ui.theme.PurplePlum
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSupplementScreen(navController: NavHostController) {
    val viewModel: AddSupplementViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val columnVerticalArrangement = Arrangement.spacedBy(20.dp)
    var showBottomSheet by remember { mutableStateOf(false) }
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = Date().time,
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                val millis = calendar.timeInMillis
                return utcTimeMillis >= millis
            }
        })
    val scaffoldState =
        rememberModalBottomSheetState()
    val context = LocalContext.current.applicationContext

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 35.dp),
        containerColor = Color.White,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            RectanglePrimaryButton(
                Modifier.padding(horizontal = 24.dp),
                label = stringResource(id = R.string.add_supplement),
                isEnabled = viewModel.isValidSupp()
            ) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, timeOfDayList[uiState.timeOfDay].time?.hour ?: 0)
                calendar.set(Calendar.MINUTE, timeOfDayList[uiState.timeOfDay].time?.minute ?: 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                val millis = calendar.timeInMillis
                val service = NotificationService(context)
                service.scheduleNotification(millis, uiState.name)
                navController.navigate(Screen.MainHomeScreen.destination) {
                    popUpTo(Screen.MainHomeScreen.destination) { inclusive = true }
                }
            }
        }
    ) {
        Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.BottomCenter) {
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = scaffoldState,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                            .background(Color.White)
                    ) {
                        DateRangePickerSample(
                            dateRangePickerState,
                        ) {
                            coroutineScope.launch {
                                showBottomSheet = !showBottomSheet
                                scaffoldState.hide()
                            }
                        }
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = columnVerticalArrangement,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 24.dp, end = 24.dp),
                    verticalArrangement = columnVerticalArrangement,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        SquareButton(
                            Modifier
                                .size(48.dp),
                            color = LilacPetalsDark,
                            icon = R.drawable.x,
                            cornerCarve = 5
                        ) {
                            navController.popBackStack()
                        }
                    }
                    HeadlineLarge(stringResource(R.string.add_supplement))
                    TextInputWithLabel(
                        label = stringResource(R.string.supplement_name),
                        labelColor = Color.DarkGray,
                        placeholder = stringResource(R.string.type_name_of_the_supplement),
                        default = uiState.name,
                        error = if (uiState.name.length >= 3 || uiState.name.isEmpty()) null else "Enter a Valid Drug Name",
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ), modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.onEvent(AddSuppStateUiEvents.Name(it))
                    }
                    HDivider()
                    Headline3(
                        text = "Supplement Form",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(formList.size) {
                        FormsSelectableCard(
                            Modifier,
                            formList[it].id,
                            uiState.form == formList[it].id,
                            formList[it].name, formList[it].icon
                        ) { i, _ ->
                            viewModel.onEvent(AddSuppStateUiEvents.Form(i))
                        }
                    }
                }
                HDivider(Modifier.padding(horizontal = 24.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Headline3(
                        text = "Dosage",
                        textAlign = TextAlign.Start
                    )
                    BodyText3Text(
                        text = "(Times a day)",
                        color = DarkGrey,
                        textAlign = TextAlign.Start
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(6) {
                        SelectableCard(
                            Modifier,
                            it,
                            uiState.dosage == it,
                            (it + 1).toString(),
                        ) { i, _ ->
                            viewModel.onEvent(AddSuppStateUiEvents.Dosage(i))
                        }
                    }
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = columnVerticalArrangement,
                ) {
                    HDivider()

                    Headline3(
                        text = "Frequency",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    val options = listOf("Daily", "Weekly", "Monthly")
                    CustomDropDown(options) {
                        viewModel.onEvent(AddSuppStateUiEvents.Frequency(it))
                    }
                    Headline3(
                        text = "Interval",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        Modifier
                            .background(LilacPetals, RoundedCornerShape(20.dp))
                            .border(
                                1.dp,
                                color = LilacPetalsDark,
                                RoundedCornerShape(20.dp)
                            )
                            .fillMaxWidth()
                            .wrapContentSize()
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                coroutineScope.launch {
                                    showBottomSheet = !showBottomSheet
                                    scaffoldState.show()
                                }

                            }
                    ) {
                        BodyTextTwo(
                            text = if (dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null) {
                                viewModel.onEvent(
                                    AddSuppStateUiEvents.StartDate(
                                        Date(dateRangePickerState.selectedStartDateMillis!!)
                                    )
                                )
                                viewModel.onEvent(
                                    AddSuppStateUiEvents.EndDate(
                                        Date(dateRangePickerState.selectedEndDateMillis!!)
                                    )
                                )
                                buildString {
                                    append("From ")
                                    append(getFormattedDate(dateRangePickerState.selectedStartDateMillis!!))
                                    append(" To ")
                                    append(getFormattedDate(dateRangePickerState.selectedEndDateMillis!!))
                                }
                            } else "Interval",
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }
                    Headline3(
                        text = "Time of day",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(timeOfDayList.size) {
                        FormsSelectableCard(
                            Modifier,
                            timeOfDayList[it].id,
                            uiState.timeOfDay == timeOfDayList[it].id,
                            timeOfDayList[it].name,
                            timeOfDayList[it].icon
                        ) { i, _ ->
                            viewModel.onEvent(AddSuppStateUiEvents.TimeOfDay(i))
                        }
                    }
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = columnVerticalArrangement,
                ) {
                    HDivider()
                    Headline3(
                        text = "Taking with meals",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    val takingWithMealsList = listOf("Before meal", "After meal", "During the meal")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        takingWithMealsList.forEachIndexed { i, s ->
                            AssistChip(
                                onClick = {
                                    viewModel.onEvent(AddSuppStateUiEvents.TakingWithMeals(i))
                                },
                                colors = if (uiState.takingWithMeals == i) AssistChipDefaults.assistChipColors(
                                    containerColor = LilacPetalsDark,
                                    labelColor = DeepBlue,
                                ) else AssistChipDefaults.assistChipColors(
                                    containerColor = LilacPetalsDark,
                                    labelColor = DarkGrey,
                                ),
                                label = {
                                    BodyTextTwo(
                                        text = s,
                                        color = if (uiState.takingWithMeals == i) DeepBlue else DarkGrey
                                    )
                                },
                                border = if (uiState.takingWithMeals == i) BorderStroke(
                                    1.dp,
                                    PurplePlum
                                ) else null,
                                shape = RoundedCornerShape(20.dp)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun AddSupplementScreenPreview() {
    AddSupplementScreen(rememberNavController())
}