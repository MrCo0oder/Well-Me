package com.codebook.wellme.ui.screens.home.activity.activityTimer

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codebook.wellme.R
import com.codebook.wellme.ui.SquareIconButton
import com.codebook.wellme.ui.theme.DarkGrey
import com.codebook.wellme.ui.theme.LilacPetalsDark
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.ComposeNodeLifecycleCallback
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.sourceInformationMarkerStart
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.service.ServiceHelper
import com.codebook.wellme.service.StopwatchService
import com.codebook.wellme.service.StopwatchState
import com.codebook.wellme.ui.BodyText1
import com.codebook.wellme.ui.BodyText3Text
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.DustGrey
import com.codebook.wellme.ui.theme.Peach
import com.codebook.wellme.ui.theme.PeachLight
import com.codebook.wellme.ui.theme.PurplePlum
import com.codebook.wellme.ui.theme.Turquoise
import com.codebook.wellme.ui.theme.Violet
import com.codebook.wellme.ui.theme.Water
import com.codebook.wellme.utils.Constants.ACTION_SERVICE_CANCEL
import com.codebook.wellme.utils.Constants.ACTION_SERVICE_START
import com.codebook.wellme.utils.Constants.ACTION_SERVICE_STOP

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ActivityTimerScreen(mainNavController: NavHostController, stopwatchService: StopwatchService) {
    val context = LocalContext.current
    val hours by stopwatchService.hours
    val minutes by stopwatchService.minutes
    val seconds by stopwatchService.seconds
    val currentState by stopwatchService.currentState

    Scaffold(Modifier.fillMaxSize(), topBar = {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, top = 40.dp),
            horizontalArrangement = Arrangement.End
        ) {
            SquareIconButton(drawableId = R.drawable.x, DarkGrey, LilacPetalsDark) {
                mainNavController.popBackStack()
            }
        }
    }, floatingActionButton = {
        var fabState by rememberSaveable {
            mutableStateOf(true)
        }
        val transition = updateTransition(targetState = fabState, label = "playPauseTransition")

        val rotation by transition.animateFloat(
            transitionSpec = {
                tween(durationMillis = 350)
            }, label = ""
        ) { state ->
            if (state) 0f else 360f
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.background(getFabBoxColor(currentState), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                ElevatedButton(
                    onClick = { /*onClick()*/ fabState = !fabState
                        ServiceHelper.triggerForegroundService(
                            context = context,
                            action = if (currentState == StopwatchState.Started) ACTION_SERVICE_STOP
                            else ACTION_SERVICE_START
                        )
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = getFabBackgroundColor(currentState),
                        disabledContainerColor = DustGrey
                    ),
                    modifier = Modifier
                        .size(124.dp)
                        .padding(10.dp),
                    enabled = /*isEnabled*/true
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AnimatedVisibility(visible = stopwatchService.currentState.value == StopwatchState.Idle) {
                            BodyText3Text(text = "Start", color = Color.White)
                        }
                        AnimatedVisibility(visible = stopwatchService.currentState.value != StopwatchState.Idle) {
                            Icon(
                                painter = painterResource(id = getFabIcon(currentState)),
                                contentDescription = null,
                                modifier = Modifier.graphicsLayer(rotationY = rotation)
                            )
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(
                modifier = Modifier
                    .size(65.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Peach,
                    disabledContainerColor = PeachLight
                ),
                onClick = {
                    ServiceHelper.triggerForegroundService(
                        context = context, action = ACTION_SERVICE_CANCEL
                    )
                },
                enabled = seconds != "00" && currentState != StopwatchState.Started,

                ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White)
                )
            }
        }

    }, floatingActionButtonPosition = FabPosition.Center) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_1_purple),
                        contentDescription = null
                    )

                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_2_yellow),
                        contentDescription = null
                    )

                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_3_peach),
                        contentDescription = null
                    )

                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f), horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_4_green),
                        contentDescription = null
                    )

                }

            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
            ) {
                Spacer(modifier = Modifier.weight(2f))
                Icon(
                    painter = painterResource(id = R.drawable.walking),
                    contentDescription = null,
                    tint = PurplePlum
                )
                BodyText1(label = "Walking")
                Row {
                    HeadlineLarge(text = " $hours : ", color = DeepBlue)
                    HeadlineLarge(text = "$minutes : ", color = DeepBlue)
                    HeadlineLarge(text = "$seconds ", color = DeepBlue)
                }
                Spacer(modifier = Modifier.weight(4f))
            }

        }
    }
}

private fun getFabIcon(state: StopwatchState): Int {
    return if (state == StopwatchState.Canceled || state == StopwatchState.Stopped) R.drawable.play else R.drawable.pause
}

private fun getFabBackgroundColor(state: StopwatchState): Color {
    return if (state == StopwatchState.Canceled || state == StopwatchState.Stopped) Turquoise else PurplePlum

}

private fun getFabBoxColor(state: StopwatchState): Color {
    return if (state == StopwatchState.Canceled || state == StopwatchState.Stopped) Water else Violet
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview
private fun Preview() {
    ActivityTimerScreen(
        mainNavController = rememberNavController(), StopwatchService()
    )
}