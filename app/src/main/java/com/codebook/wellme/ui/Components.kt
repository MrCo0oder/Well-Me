package com.codebook.wellme.ui

import android.text.style.BackgroundColorSpan
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.codebook.wellme.R
import com.codebook.wellme.ui.theme.Alert
import com.codebook.wellme.ui.theme.ColdGrey
import com.codebook.wellme.ui.theme.DarkGrey
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.DustGrey
import com.codebook.wellme.ui.theme.LilacPetals
import com.codebook.wellme.ui.theme.LilacPetalsDark
import com.codebook.wellme.ui.theme.PurplePlum
import com.codebook.wellme.ui.theme.Turquoise
import com.codebook.wellme.ui.theme.Violet
import com.codebook.wellme.ui.theme.VioletLight
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun RectanglePrimaryButton(
    modifier: Modifier = Modifier, label: String, isEnabled: Boolean = true, onClick: () -> Unit
) {
    ElevatedButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PurplePlum, disabledContainerColor = DustGrey
        ),
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = SpaceEvenly
        ) {
            ButtonText1(label, Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.arrow_long_right),
                contentDescription = null,
                contentScale = ContentScale.None
            )
        }
    }
}

@Composable
private fun ButtonText1(label: String, modifier: Modifier = Modifier) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelLarge.copy(color = White),
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BodyText3Text(
    text: String,
    color: Color = ColdGrey,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(color = color),
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun HeadLine2Text(
    text: String, modifier: Modifier = Modifier,
    color: Color = ColdGrey,
    textAlign: TextAlign = TextAlign.Center,

    ) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium.copy(color = color),
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun HeadlineLarge(
    text: String,
    color: Color = ColdGrey,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(color = color),
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun Headline3(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ColdGrey,
    textAlign: TextAlign = TextAlign.Center,

    ) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall.copy(color = color),
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun BodyTextTwo(
    text: String,
    color: Color = ColdGrey,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(color = color),
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun ButtonTextTwo(
    text: String,
    color: Color = ColdGrey,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium.copy(color = color),
        textAlign = textAlign,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerScreen(modifier: Modifier, navigate: () -> Unit) {
    val scope = rememberCoroutineScope()
    val pageCount = 3
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        pageCount
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalPager(
                modifier = modifier.fillMaxSize(),
                state = pagerState,
                userScrollEnabled = true,
            ) { current ->
                when (current) {
                    0 -> {
                        OnBoardingComponent(
                            modifier = Modifier.weight(1f),
                            image = R.drawable.calm,
                            header = "Keep calm and stay\nin control",
                            subHeading = "You can check your health with just one look.",
                        )
                    }

                    1 -> {
                        OnBoardingComponent(
                            modifier = Modifier.weight(1f),
                            image = R.drawable.pills,
                            header = "Don’t miss a single\npill, ever!",
                            subHeading = "Plan your supplementation in details.",
                        )
                    }

                    else -> {
                        OnBoardingComponent(
                            modifier = Modifier.weight(1f),
                            image = R.drawable.yoga,
                            header = "Exercise more\n& breathe better",
                            subHeading = "Learn, measure, set daily goals.",
                        )
                    }
                }
            }
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
            SquareButton(Modifier.size(88.dp)) {
                scope.launch {
                    if (pagerState.currentPage != 2) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else navigate()
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Indicator(pagerState = pagerState)
        }
    }
}

@Composable
fun SquareButton(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.arrow_long_right,
    color: Color = PurplePlum,
    cornerCarve: Int = 20,
    navigate: () -> Unit
) {
    IconButton(
        onClick = {
            navigate()
        },
        modifier = modifier.clip(RoundedCornerShape(cornerCarve.dp)),
        colors = IconButtonDefaults.iconButtonColors(containerColor = color)
    ) {
        Icon(
            painter = painterResource(id = icon), contentDescription = null, tint = Unspecified
        )
    }
}

@Composable
fun OnBoardingComponent(
    modifier: Modifier = Modifier, image: Int, header: String, subHeading: String
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(image),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier.size(350.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        HeadlineLarge(text = header, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        BodyText3Text(text = subHeading, modifier = Modifier.fillMaxWidth())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Indicator(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp),
        horizontalArrangement = Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) PurplePlum else White
            Box(modifier = Modifier
                .padding(4.dp)
                .width(70.dp)
                .height(6.dp)
                .clip(CircleShape)
                .background(color)
                .clickable {
                    coroutineScope.launch {
                        pagerState.scrollToPage(iteration)
                    }
                })
        }
    }
}

@Composable
fun OutlinedButtonPurple(
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onClick() },
        border = BorderStroke(1.dp, PurplePlum),
        modifier = buttonModifier.wrapContentSize()
    ) {
        ButtonTextTwo(text = text, modifier = modifier, color = PurplePlum)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputWithLabel(
    modifier: Modifier = Modifier,
    label: String,
    labelColor: Color = ColdGrey,
    placeholder: String = "",
    default: String,
    error: String?,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
    ),
    leadingIcon: Int? = null,
    onValueChange: (String) -> Unit
) {
    var value by remember {
        mutableStateOf(default)
    }
    val showPassword = remember { mutableStateOf(true) }
    val showIcon = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current
    Column(modifier.wrapContentSize()) {
        BodyText3Text(text = label, textAlign = TextAlign.Start, color = labelColor)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (isPassword) {
                    value = it.trim()
                    onValueChange(it.trim())
                    if (it.isNotEmpty()) showIcon.value = true
                } else {
                    value = it
                    onValueChange(it)
                    if (it.isNotEmpty()) showIcon.value = true
                }
            },
            Modifier.fillMaxWidth(), maxLines = 1, isError = !error.isNullOrEmpty(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = LilacPetals,
                focusedIndicatorColor = Violet,
                unfocusedIndicatorColor = LilacPetalsDark,
                errorIndicatorColor = Alert,
                errorCursorColor = Alert,
                errorTrailingIconColor = Alert,
                errorSupportingTextColor = Alert
            ),
            placeholder = {
                BodyTextTwo(text = placeholder, color = labelColor)
            },
            shape = RoundedCornerShape(20.dp),
            visualTransformation = if (isPassword && showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
            supportingText = {
                if (!error?.trim().isNullOrEmpty()) BodyText3Text(
                    text = error.toString(),
                    color = Alert,
                    TextAlign.Start
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(labelColor),
            singleLine = true,
            keyboardActions = KeyboardActions {
                if (!isPassword) {
                    showIcon.value = false
                    localFocusManager.moveFocus(FocusDirection.Down)
                } else {
                    localFocusManager.clearFocus()
                }
            },
            trailingIcon = {
                if (isPassword) {
                    val icon =
                        if (showPassword.value) R.drawable.show_password else R.drawable.hide_password
                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                        )
                    }
                }
            },
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null,
                        tint = labelColor
                    )
                }
            },
            keyboardOptions = if (isPassword) {
                KeyboardOptions(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                )
            } else keyboardOptions,
        )
    }
}

@Composable
fun HDivider(modifier: Modifier = Modifier, color: Color = Color.LightGray) {
    Box(
        modifier = modifier
            .background(color)
            .height(1.dp)
            .fillMaxWidth()
    )
}

@Composable
fun SocialMediaButton(icon: Int, onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier.size(70.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(20.dp)
    ) {
        Image(
            painter = painterResource(id = icon), contentDescription = null, Modifier.size(30.dp)
        )
    }
}

@Composable
fun ClickableText(label: String, labelColor: Color = PurplePlum, onClick: () -> Unit) {
    TextButton(onClick = { onClick() }) {
        BodyText3Text(text = label, color = labelColor)
    }
}

@Composable
fun CustomCheckbox(checkBox: MutableState<Boolean>, interactionSource: (Boolean) -> Unit) {
    Checkbox(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.DarkGray), RoundedCornerShape(5.dp)
            )
            .padding(4.dp)
            .size(16.dp), checked = checkBox.value, onCheckedChange = {
            checkBox.value = it
            interactionSource(it)
        }, colors = CheckboxDefaults.colors(
            checkmarkColor = PurplePlum,
            checkedColor = Color.Transparent,
            uncheckedColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordValidationComponent(label: String, isValid: Boolean) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        if (isValid) Icon(
            painter = painterResource(id = R.drawable.check),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Unspecified
        ) else Icon(
            painter = painterResource(id = R.drawable.no_data),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Alert
        )
        Spacer(modifier = Modifier.width(8.dp))
        BodyText3Text(
            text = label, color = Color.DarkGray, TextAlign.Justify
        )
    }
}

@Composable
fun BottomNavIcon(drawableId: Int, contentDescription: String) {
    Icon(
        painter = painterResource(id = drawableId),
        contentDescription = contentDescription,
        tint = Unspecified
    )
}

@Composable
fun SquareIconButton(
    drawableId: Int,
    tint: Color = DarkGrey,
    backgroundColor: Color = White,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(drawableId),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthStateCard(
    modifier: Modifier = Modifier,
    icon: Int,
    id: Int,
    cardColor: Color,
    boxColor: Color,
    vitalSignLabel: String,
    vitalSignValue: String,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(id) },
        colors = CardDefaults.cardColors(cardColor, contentColor = DustGrey),
        modifier = modifier.size(120.dp, 160.dp), enabled = false,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(boxColor), contentAlignment = Alignment.Center

                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.padding(6.dp)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.arrow_long_right),
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.padding()
                )
            }
            Column(Modifier.fillMaxWidth()) {
                BodyText3Text(text = vitalSignLabel)
                Headline3(text = vitalSignValue, color = DeepBlue)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListCard(
    modifier: Modifier = Modifier,
    icon: Int,
    id: Int,
    cardColor: Color,
    label: String,
    itemName: String,
    progress: Float,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(id) },
        colors = CardDefaults.cardColors(cardColor, contentColor = DustGrey),
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
            .shadow(elevation = 10.dp, spotColor = PurplePlum, ambientColor = PurplePlum)
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(32.dp)
            )
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Headline3(
                    text = itemName,
                    color = DeepBlue,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                if (progress in 0.1f..99.9f) OutlinedButtonPurple(
                    Modifier.padding(16.dp, 0.dp),
                    text = label
                ) {

                }
                else {
                    BodyText3Text(
                        text = label,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }
            when (progress) {
                0f -> {
                    Image(
                        painter = painterResource(id = R.drawable.checkbox_empty),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(32.dp)
                    )
                }

                100f -> {
                    Image(
                        painter = painterResource(id = R.drawable.checkbox_full),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(32.dp)
                    )
                }

                else -> {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.percentage_progress),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,

                            )
                        CircularProgressIndicator(
                            progress / 100,
                            modifier = Modifier.size(36.dp),
                            color = Turquoise,
                            strokeWidth = 3.dp
                        )
                        Text(
                            text = "${progress}%",

                            // Percentages
                            style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.notosans_regular, FontWeight.W600
                                    )
                                ),
                                color = ColdGrey,
                                letterSpacing = 0.02.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeHeaderRow(
    headerText: String, icon: Int, label: String, isClickable: Boolean = false, onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.headlineMedium.copy(DeepBlue),

            )
        Row(modifier = if (isClickable) Modifier
            .clip(RoundedCornerShape(5.dp))
            .clickable { onClick() } else Modifier) {
            Icon(
                painter = painterResource(id = icon), contentDescription = null, tint = PurplePlum
            )
            Spacer(modifier = Modifier.width(8.dp))
            BodyText3Text(label, color = PurplePlum)
        }
    }
}

@Composable
fun Dialog(
    onDismissRequest: () -> Unit, onX: () -> Unit, onActivity: () -> Unit, onSupplement: () -> Unit
) {
    androidx.compose.ui.window.Dialog(
        onDismissRequest = { onDismissRequest() }, DialogProperties(
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = PurplePlum.copy(0.4f)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(Modifier.size(312.dp, 306.dp), verticalArrangement = Center) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = End
                    ) {
                        SquareButton(
                            Modifier.size(48.dp),
                            color = White,
                            icon = R.drawable.x,
                            cornerCarve = 10
                        ) {
                            onX()
                        }
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 40.dp, spotColor = PurplePlum, ambientColor = PurplePlum
                            )
                            .background(White, RoundedCornerShape(16.dp))
                            .padding(16.dp)
                            .weight(1f)
                    ) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = SpaceEvenly,
                            horizontalAlignment = CenterHorizontally
                        ) {
                            HeadLine2Text(text = "Choose task category")
                            OutlinedButtonPurple(
                                Modifier.fillMaxWidth(), text = "Activity"
                            ) {
                                onActivity()
                            }
                            OutlinedButtonPurple(
                                Modifier.fillMaxWidth(), text = "Supplement"
                            ) {
                                onSupplement()
                            }
                        }

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableCard(
    modifier: Modifier, id: Int, isSelected: Boolean, label: String, onClick: (Int, Boolean) -> Unit
) {
    var selected = isSelected
    Card(
        onClick = {
            if (selected) onClick(-1, true) else onClick(id, false)
            selected = !selected
        },
        colors = CardDefaults.cardColors(LilacPetalsDark),
        modifier = modifier.size(52.dp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = if (selected) PurplePlum else Color.Transparent
                )
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Headline3(text = label, color = if (selected) DeepBlue else DarkGrey)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormsSelectableCard(
    modifier: Modifier,
    id: Int,
    isSelected: Boolean,
    label: String,
    icon: Int,
    onClick: (Int, Boolean) -> Unit
) {
    var selected = isSelected
    Column(Modifier.wrapContentSize()) {
        Card(
            onClick = {
                if (selected) onClick(-1, true) else onClick(id, false)
                selected = !selected
            },
            colors = CardDefaults.cardColors(LilacPetalsDark),
            modifier = modifier.size(64.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(20.dp),
                        color = if (selected) PurplePlum else Color.Transparent
                    )
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = if (selected) DeepBlue else DarkGrey,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ButtonTextTwo(
            text = label,
            color = if (selected) DeepBlue else DarkGrey,
            modifier = Modifier.width(64.dp)
        )
    }
}

@Composable
fun CustomDropDown(
    list: List<String> = listOf("Daily", "Weekly", "Monthly"),
    onChange: (Int) -> Unit
) {
    var mExpanded by remember { mutableStateOf(false) }
    var selectedElement by remember { mutableStateOf(list[0]) }
    LaunchedEffect(key1 = selectedElement) {
        onChange(list.indexOf(selectedElement))
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LilacPetals, RoundedCornerShape(20.dp))
            .wrapContentHeight()
            .border(
                1.dp,
                color = if (mExpanded) PurplePlum else LilacPetalsDark,
                RoundedCornerShape(20.dp)
            )

    ) {
        Column(
            Modifier.padding(vertical = 13.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { mExpanded = !mExpanded }
                    .padding(vertical = 5.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BodyTextTwo(text = selectedElement)
                if (mExpanded)
                    Icon(
                        painter = painterResource(id = R.drawable.icon_collapse),
                        contentDescription = null, tint = Unspecified
                    ) else Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null, tint = Unspecified
                )
            }
            AnimatedVisibility(visible = mExpanded) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    list.forEach { s ->
                        HDivider(color = Violet)
                        BodyTextTwo(
                            text = s,
                            DeepBlue,
                            textAlign = TextAlign.Start,
                            modifier = if (s == selectedElement) Modifier
                                .background(
                                    VioletLight,
                                    RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                            else
                                Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(6.dp))
                                    .clickable {
                                        selectedElement = s
                                        mExpanded = !mExpanded
                                    }
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerSample(
    state: DateRangePickerState,
    onClick: () -> Unit
) {
    DateRangePicker(
        state,
        modifier = Modifier,
        title = {
        },
        dateFormatter = DatePickerDefaults.dateFormatter("MMM YYYY", "dd MM yyyy", "dd MM yyyy"),
        headline = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(Modifier.weight(1f)) {
                    (if (state.selectedStartDateMillis != null) state.selectedStartDateMillis?.let {
                        getFormattedDate(
                            it
                        )
                    } else "Start Date")?.let { ButtonTextTwo(text = it, color = PurplePlum) }
                }
                Box(Modifier.weight(1f)) {
                    (if (state.selectedEndDateMillis != null) state.selectedEndDateMillis?.let {
                        getFormattedDate(
                            it
                        )
                    } else "End Date")?.let { ButtonTextTwo(text = it, color = PurplePlum) }
                }
                Box(
                    Modifier
                        .weight(0.2f)
                        .clickable { onClick() }) {
                    Icon(Icons.Default.Check, contentDescription = "Okk", tint = PurplePlum)
                }

            }
        },
        showModeToggle = false,
        colors = DatePickerDefaults.colors(
            containerColor = Turquoise,
            titleContentColor = PurplePlum,
            headlineContentColor = PurplePlum,
            weekdayContentColor = Black,
            subheadContentColor = Black,
            currentYearContentColor = Color.Red,
            selectedYearContainerColor = Color.Red,
            disabledDayContentColor = Color.Gray,
            todayDateBorderColor = Turquoise,
            todayContentColor = Turquoise,
            dayInSelectionRangeContainerColor = VioletLight,
            dayInSelectionRangeContentColor = Black,
            selectedDayContainerColor = Violet
        )
    )
}

fun getFormattedDate(timeInMillis: Long): String {
    val calender = Calendar.getInstance()
    calender.timeInMillis = timeInMillis
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(calender.timeInMillis)
}

@Composable
fun ActivitySelectableCard(
    modifier: Modifier,
    id: Int,
    isSelected: Boolean,
    label: String,
    icon: Int,
    onClick: (Int, Boolean) -> Unit
) {
    var selected = isSelected
    Column(Modifier.wrapContentSize()) {
        Card(
            onClick = {
                if (selected) onClick(-1, true) else onClick(id, false)
                selected = !selected
            },
            colors = CardDefaults.cardColors(LilacPetalsDark),
            modifier = modifier.size(66.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = 4.dp,
                        shape = CircleShape,
                        color = if (selected) Violet else Color.Transparent
                    )
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = if (selected) ColdGrey else Violet,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ButtonTextTwo(
            text = label,
            color = if (selected) DeepBlue else DarkGrey,
            modifier = Modifier.width(66.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = false)
@Composable
private fun PreviewFun() {
//    ToDoListCard(
//        icon = R.drawable.breath_rate,
//        id = 0,
//        cardColor = White,
//        label = "Continue exercise",
//        itemName = "Breath Rate", progress = 99f
//    ) {
//
//    }
//    HorizontalPagerScreen(Modifier) {}
//    Dialog({}, {}, {}) {}
    ActivitySelectableCard(
        Modifier,
        0,
        true,
        "1",
        R.drawable.pill
    ) { _, _ -> }
//    TextInputWithLabel(
//        label = stringResource(R.string.supplement_name),
//        labelColor = Color.DarkGray,
//        placeholder = stringResource(R.string.type_name_of_the_supplement),
//        default = /*uiState.email*/"",
//        error = /*uiState.email.validateEmail()*/"",
//        keyboardOptions = KeyboardOptions(
//            imeAction = ImeAction.Next,
//            keyboardType = KeyboardType.Email
//        ), modifier = Modifier.fillMaxWidth()
//    ) {
////                viewModel.onEvent(LoginStateUiEvents.Email(it))
//    }
    FlowRow {

    }
}