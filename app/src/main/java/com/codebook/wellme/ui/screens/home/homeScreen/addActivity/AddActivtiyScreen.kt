package com.codebook.wellme.ui.screens.home.homeScreen.addActivity

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.navigation.Screen
import com.codebook.wellme.ui.ActivitySelectableCard
import com.codebook.wellme.ui.BodyText3Text
import com.codebook.wellme.ui.FormsSelectableCard
import com.codebook.wellme.ui.HDivider
import com.codebook.wellme.ui.HeadLine2Text
import com.codebook.wellme.ui.Headline3
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SquareButton
import com.codebook.wellme.ui.screens.home.homeScreen.addSupplement.Supplements
import com.codebook.wellme.ui.theme.DarkGrey
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.LilacPetals
import com.codebook.wellme.ui.theme.LilacPetalsDark
import com.codebook.wellme.ui.theme.VioletLight
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddActivityScreen(navController: NavHostController) {
    val viewModel: AddActivityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val columnVerticalArrangement = Arrangement.spacedBy(20.dp)

    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 35.dp),
        containerColor = Color.White,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            RectanglePrimaryButton(
                Modifier.padding(horizontal = 24.dp),
                label = stringResource(R.string.add_activity),
                isEnabled = viewModel.isValidScreen()
            ) {
                Log.i("Activ", "${Activities.activitiesList[uiState.type].name} \n ${Supplements.timeOfDayList[uiState.timeOfDay].name} \n at ${uiState.durationMins}:${uiState.durationSeconds}")
                navController.navigate(Screen.MainHomeScreen.destination) {
                    popUpTo(Screen.MainHomeScreen.destination) { inclusive = true }
                }
            }
        }
    ) {
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
                HeadlineLarge(stringResource(R.string.add_activity))

                HDivider()
                Headline3(
                    text = stringResource(id = R.string.choose_the_type_of_activity),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                FlowRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Activities.activitiesList.forEach {
                        ActivitySelectableCard(
                            Modifier,
                            it.id,
                            it.id == uiState.type,
                            it.name, it.icon
                        ) { i, _ ->
                            viewModel.onEvent(AddActivityStateUiEvents.Type(i))
                        }
                    }
                }
                HDivider()
                Headline3(
                    text = stringResource(R.string.time_of_day),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(Supplements.timeOfDayList.size) {
                    FormsSelectableCard(
                        Modifier,
                        Supplements.timeOfDayList[it].id,
                        uiState.timeOfDay == Supplements.timeOfDayList[it].id,
                        Supplements.timeOfDayList[it].name,
                        Supplements.timeOfDayList[it].icon
                    ) { i, _ ->
                        viewModel.onEvent(AddActivityStateUiEvents.TimeOfDay(i))
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
                    text = stringResource(R.string.activity_duration),
                    textAlign = TextAlign.Start
                )
                BodyText3Text(
                    text = stringResource(R.string.hours_minutes),
                    color = DarkGrey,
                    textAlign = TextAlign.Start
                )

            }
            val minState = rememberFWheelPickerState()
            val secState = rememberFWheelPickerState()
            Box(
                Modifier
                    .width(214.dp)
                    .height(206.dp)
                    .background(LilacPetals, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement =
                    Arrangement.Start
                ) {
                    FVerticalWheelPicker(
                        state = minState,
                        unfocusedCount = 2,
                        count = 60, modifier = Modifier.weight(1f), itemHeight = 40.dp,
                        focus = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = VioletLight.copy(0.4f),
                                        RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                                    )
                            )
                        },
                    ) {
                        HeadLine2Text(
                            String.format("%02d", it),
                            color = DeepBlue,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    HeadLine2Text(
                        " : ", color = DeepBlue, textAlign = TextAlign.Start,
                        modifier = Modifier
                            .background(color = VioletLight.copy(0.4f))
                            .padding(top = 5.dp)
                            .height(35.dp)
                    )
                    FVerticalWheelPicker(
                        state = secState,
                        unfocusedCount = 2,
                        count = 61, modifier = Modifier.weight(1f),
                        itemHeight = 40.dp,
                        focus = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = VioletLight.copy(0.4f),
                                        RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                                    )
                            )
                        },
                    ) {
                        HeadLine2Text(
                            String.format("%02d", it),
                            color = DeepBlue,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            LaunchedEffect(key1 = minState) {
                snapshotFlow { minState.currentIndex }
                    .collect {
                        viewModel.onEvent(AddActivityStateUiEvents.DurationMins(minState.currentIndex))
                    }
            }
            LaunchedEffect(key1 = secState) {
                snapshotFlow { secState.currentIndex }
                    .collect {
                        viewModel.onEvent(AddActivityStateUiEvents.DurationSeconds(secState.currentIndex))
                    }
            }

            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}

@Preview()
@Composable
private fun AddSupplementScreenPreview() {
    AddActivityScreen(rememberNavController())
}