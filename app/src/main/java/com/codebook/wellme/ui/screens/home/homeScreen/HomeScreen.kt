package com.codebook.wellme.ui.screens.home.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.ui.Dialog
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.HealthStateCard
import com.codebook.wellme.ui.HomeHeaderRow
import com.codebook.wellme.ui.SquareIconButton
import com.codebook.wellme.ui.ToDoListCard
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.Peach
import com.codebook.wellme.ui.theme.PeachLight
import com.codebook.wellme.ui.theme.Violet
import com.codebook.wellme.ui.theme.VioletLight
import com.codebook.wellme.ui.theme.Water
import com.codebook.wellme.ui.theme.WaterLight
import com.codebook.wellme.utils.Screen

@Composable
fun HomeScreen(navController: NavHostController, mainNavController: NavHostController) {
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        Column(
            Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.Start
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, bottom = 0.dp, start = 24.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_small),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(40.dp)
                )
                SquareIconButton(drawableId = R.drawable.notification) {

                }
            }
            HeadlineLarge(
                text = "Hi Ahmed!", color = DeepBlue, modifier = Modifier
                    .padding(horizontal = 24.dp)
            )
            HomeHeaderRow("Health stats", R.drawable.calendar, "Weekly", onClick = {})
            LazyRow(
                Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    HealthStateCard(
                        icon = R.drawable.breath_rate,
                        id = 0,
                        cardColor = VioletLight,
                        boxColor = Violet,
                        vitalSignLabel = "Breath Rate",
                        vitalSignValue = "12 BrPM"
                    ) {

                    }
                }
                item {
                    HealthStateCard(
                        icon = R.drawable.heart_rate,
                        id = 1,
                        cardColor = WaterLight,
                        boxColor = Water,
                        vitalSignLabel = "Heart Rate",
                        vitalSignValue = "68 BPM"
                    ) {

                    }
                }
                item {
                    HealthStateCard(
                        icon = R.drawable.blood_pressure,
                        id = 2,
                        cardColor = PeachLight,
                        boxColor = Peach,
                        vitalSignLabel = "Blood Pressure",
                        vitalSignValue = "122 / 87"
                    ) {

                    }
                }
            }
            HomeHeaderRow(
                headerText = "To-do list",
                icon = R.drawable.add,
                label = "Add task",
                isClickable = true
            ) {
                setShowDialog(true)

            }
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 5.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    ToDoListCard(
                        icon = R.drawable.breath,
                        id = 0,
                        cardColor = White,
                        label = "Continue exercise",
                        itemName = "Breath Rate",
                        progress = 6f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.ic_pills,
                        id = 1,
                        cardColor = White,
                        itemName = "Omega 3",
                        progress = 100f,
                        label = "1 pill after meal",
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.steps,
                        id = 2,
                        cardColor = White,
                        label = "Target 10 000 Step",
                        itemName = "Step Goal", progress = 0f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.breath,
                        id = 0,
                        cardColor = White,
                        label = "Continue exercise",
                        itemName = "Breath Rate",
                        progress = 57.6f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.ic_pills,
                        id = 1,
                        cardColor = White,
                        itemName = "Omega 3",
                        progress = 100f,
                        label = "1 pill after meal",
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.steps,
                        id = 2,
                        cardColor = White,
                        label = "Target 10 000 Step",
                        itemName = "Step Goal", progress = 0f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.breath,
                        id = 0,
                        cardColor = White,
                        label = "Continue exercise",
                        itemName = "Breath Rate",
                        progress = 6f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.ic_pills,
                        id = 1,
                        cardColor = White,
                        itemName = "Omega 3",
                        progress = 100f,
                        label = "1 pill after meal",
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.steps,
                        id = 2,
                        cardColor = White,
                        label = "Target 10 000 Step",
                        itemName = "Step Goal", progress = 0f
                    ) {

                    }
                }
            }
        }
        if (showDialog)
            Dialog({
                setShowDialog(false)
            }, {
                setShowDialog(false)
            }, {
                setShowDialog(false)
            }, {
                setShowDialog(false)
                mainNavController.navigate(Screen.AddSupplementScreen.destination)
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreen(navController = rememberNavController(), mainNavController = rememberNavController())
}