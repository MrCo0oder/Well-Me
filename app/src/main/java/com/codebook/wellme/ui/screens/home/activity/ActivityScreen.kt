package com.codebook.wellme.ui.screens.home.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.navigation.Screen
import com.codebook.wellme.ui.Headline3
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SquareIconButton
import com.codebook.wellme.ui.ToDoListCard
import com.codebook.wellme.ui.theme.PurplePlum

@Composable
fun ActivityScreen(navController: NavHostController, mainNavController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, bottom = 0.dp, start = 24.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                SquareIconButton(drawableId = R.drawable.arrow_long_left, PurplePlum) {

                }
                Spacer(modifier = Modifier.width(12.dp))
                Headline3(text = stringResource(R.string.activities))
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(
                        color = Color(0xFFD0D1FF),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .padding(vertical = 16.dp)
                    .wrapContentHeight(), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RectanglePrimaryButton(
                    Modifier.fillMaxWidth(0.9f),
                    label = stringResource(R.string.start_activity_now),
                    isEnabled = true
                ) {
//                    navController.navigate(Screen.MainHomeScreen.destination) {
//                        popUpTo(Screen.LoginScreen.destination) {
//                            inclusive = true
//                        }
//                    }
                }
            }
            Headline3(
                text = stringResource(R.string.today_s_summary),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
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
                        cardColor = Color.White,
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
                        cardColor = Color.White,
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
                        cardColor = Color.White,
                        label = "Target 10 000 Step",
                        itemName = "Step Goal", progress = 0f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.breath,
                        id = 0,
                        cardColor = Color.White,
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
                        cardColor = Color.White,
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
                        cardColor = Color.White,
                        label = "Target 10 000 Step",
                        itemName = "Step Goal", progress = 0f
                    ) {

                    }
                }
                item {
                    ToDoListCard(
                        icon = R.drawable.breath,
                        id = 0,
                        cardColor = Color.White,
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
                        cardColor = Color.White,
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
                        cardColor = Color.White,
                        label = "Target 10 000 Step",
                        itemName = "Step Goal", progress = 0f
                    ) {

                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ActivityScreen(
        navController = rememberNavController(),
        mainNavController = rememberNavController()
    )
}