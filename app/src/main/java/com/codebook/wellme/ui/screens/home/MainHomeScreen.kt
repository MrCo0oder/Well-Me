package com.codebook.wellme.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.navigation.HomeNavigationGraph
import com.codebook.wellme.ui.BottomNavIcon
import com.codebook.wellme.ui.theme.LilacPetals
import com.codebook.wellme.ui.theme.VioletLight
import com.codebook.wellme.utils.Screen
import kotlin.math.roundToInt

@Composable
fun MainHomeScreen(navController: NavHostController) {
    HomeScreenContent(rememberNavController(), navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    navController: NavHostController,
    mainNavController: NavHostController
) {
    val bottomBarHeight = 104.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue =
                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    var bottomState by remember {
        mutableStateOf(Screen.HomeScreen.destination)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        color = LilacPetals
    ) {
        Scaffold(
            Modifier
                .background(LilacPetals)
                .nestedScroll(nestedScrollConnection),
            containerColor = LilacPetals,
            bottomBar = {
                NavigationBar(
                    Modifier
                        .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
                        .height(bottomBarHeight)
                        .offset(
                            x = 0.dp,
                            y = -bottomBarOffsetHeightPx.floatValue.roundToInt().dp
                        ),
                    containerColor = White
                ) {
                    Spacer(modifier = Modifier.width(45.dp))
                    NavigationBarItem(selected = bottomState == Screen.HomeScreen.destination,
                        colors = NavigationBarItemDefaults.colors(indicatorColor = VioletLight),
                        onClick = {
                            bottomState = Screen.HomeScreen.destination
                        },
                        enabled = bottomState != Screen.HomeScreen.destination,
                        icon = {
                            if (bottomState == Screen.HomeScreen.destination)
                                BottomNavIcon(
                                    drawableId = R.drawable.home_filled,
                                    contentDescription = Screen.HomeScreen.destination
                                )
                            else BottomNavIcon(
                                drawableId = R.drawable.home,
                                contentDescription = Screen.HomeScreen.destination
                            )
                        })
                    NavigationBarItem(selected = bottomState == Screen.ActivityScreen.destination,
                        colors = NavigationBarItemDefaults.colors(indicatorColor = VioletLight),
                        onClick = { bottomState = Screen.ActivityScreen.destination },
                        enabled = bottomState != Screen.ActivityScreen.destination,
                        icon = {
                            if (bottomState == Screen.ActivityScreen.destination)
                                BottomNavIcon(
                                    drawableId = R.drawable.activity_filled,
                                    contentDescription = Screen.ActivityScreen.destination
                                )
                            else BottomNavIcon(
                                drawableId = R.drawable.activity,
                                contentDescription = Screen.ActivityScreen.destination
                            )
                        })
                    NavigationBarItem(selected = bottomState == Screen.ProgressScreen.destination,
                        colors = NavigationBarItemDefaults.colors(indicatorColor = VioletLight),
                        onClick = { bottomState = Screen.ProgressScreen.destination },
                        enabled = bottomState != Screen.ProgressScreen.destination,
                        icon = {
                            if (bottomState == Screen.ProgressScreen.destination)
                                BottomNavIcon(
                                    R.drawable.progress_filled,
                                    Screen.ProgressScreen.destination,
                                ) else
                                BottomNavIcon(
                                    R.drawable.progress,
                                    Screen.ProgressScreen.destination,
                                )
                        })
                    NavigationBarItem(selected = bottomState == Screen.SettingsScreen.destination,
                        colors = NavigationBarItemDefaults.colors(indicatorColor = VioletLight),
                        onClick = { bottomState = Screen.SettingsScreen.destination },
                        enabled = bottomState != Screen.SettingsScreen.destination,
                        icon = {
                            if (bottomState == Screen.SettingsScreen.destination)
                                BottomNavIcon(
                                    drawableId = R.drawable.settings_filled,
                                    contentDescription = Screen.SettingsScreen.destination
                                )
                            else BottomNavIcon(
                                drawableId = R.drawable.settings,
                                contentDescription = Screen.SettingsScreen.destination
                            )
                        })
                    Spacer(modifier = Modifier.width(45.dp))
                }
            },
        ) {
//            Column(
//                Modifier
//                    .fillMaxSize()
////                    .padding(start = 24.dp, end = 24.dp, top = 30.dp)
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(20.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
            HomeNavigationGraph(navController, mainNavController)
//            }
        }
    }
    LaunchedEffect(key1 = bottomState) {
        navController.navigate(bottomState) {
            navController.currentDestination?.route?.let { popUpTo(it) { inclusive = true } }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
    HomeScreenContent(navController = rememberNavController(), rememberNavController())
}