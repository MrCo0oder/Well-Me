package com.codebook.wellme.ui.screens.home.activity.startingActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.codebook.wellme.ui.ActivitySelectableCard
import com.codebook.wellme.ui.Headline3
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SquareIconButton
import com.codebook.wellme.ui.screens.home.homeScreen.addActivity.Activities
import com.codebook.wellme.ui.screens.home.homeScreen.addSupplement.AddSupplementViewModel
import com.codebook.wellme.ui.theme.DarkGrey
import com.codebook.wellme.ui.theme.LilacPetalsDark

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StartingActivityScreen(mainNavController: NavHostController) {
    val viewModel: StartingActivityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                SquareIconButton(drawableId = R.drawable.x, DarkGrey, LilacPetalsDark) {
                    mainNavController.popBackStack()
                }
            }
            HeadlineLarge(
                text = stringResource(R.string.start_activity),
                modifier = Modifier.fillMaxWidth()
            )
            Headline3(
                text = stringResource(R.string.choose_the_type_of_activity),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                textAlign = TextAlign.Start
            )
            FlowRow(
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
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
                        viewModel.onEvent(StartingActivityStateUiEvents.Type(i))
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            RectanglePrimaryButton(
                Modifier.fillMaxWidth(0.9f),
                label = stringResource(R.string.start_now),
                isEnabled = viewModel.isValidScreen()
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    StartingActivityScreen(
        mainNavController = rememberNavController()
    )
}