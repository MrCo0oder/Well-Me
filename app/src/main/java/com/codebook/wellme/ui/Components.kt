package com.codebook.wellme.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codebook.wellme.R
import com.codebook.wellme.ui.theme.Alert
import com.codebook.wellme.ui.theme.ColdGrey
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.DustGrey
import com.codebook.wellme.ui.theme.LilacPetals
import com.codebook.wellme.ui.theme.LilacPetalsDark
import com.codebook.wellme.ui.theme.PurplePlum
import com.codebook.wellme.ui.theme.Turquoise
import com.codebook.wellme.ui.theme.Violet
import kotlinx.coroutines.launch

@Composable
fun RectanglePrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PurplePlum,
            disabledContainerColor = DustGrey
        ),
        modifier = modifier.fillMaxWidth(), enabled = isEnabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
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
fun SubHeadingText(
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
fun HeadlineLarge(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(color = DeepBlue),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun BodyTextTwo(
    text: String, color: Color = ColdGrey,
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
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalPager(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
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
        SquareButton {
            scope.launch {
                if (pagerState.currentPage != 2) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else navigate()
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Indicator(pagerState = pagerState)
    }
}

@Composable
fun SquareButton(
    navigate: () -> Unit
) {
    Button(
        onClick = {
            navigate()
        },
        modifier = Modifier.size(88.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = PurplePlum)
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_long_right),
            contentDescription = null
        )
    }
}

@Composable
fun OnBoardingComponent(
    modifier: Modifier = Modifier,
    image: Int,
    header: String,
    subHeading: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        HeadlineLarge(text = header)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeadingText(text = subHeading)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Indicator(pagerState: PagerState) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) PurplePlum else White
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .width(58.dp)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(color)

            )
        }
    }
}

@Composable
fun OutlinedButtonPurple(onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        border = BorderStroke(1.dp, PurplePlum),
        modifier = Modifier.padding(vertical = 35.dp, horizontal = 24.dp)
    ) {
        Text(
            text = "Skip Intro",
            style = MaterialTheme.typography.labelMedium.copy(color = PurplePlum)
        )
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
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text
    ), leadingIcon: Int,
    onValueChange: (String) -> Unit
) {
    var value by remember {
        mutableStateOf(default)
    }
    val showPassword = remember { mutableStateOf(true) }
    val showIcon = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current
    Column(modifier.wrapContentSize()) {
        SubHeadingText(text = label, textAlign = TextAlign.Start, color = labelColor)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (isPassword) {
                    value = it.trim()
                    onValueChange(it.trim())
                    if (it.isNotEmpty())
                        showIcon.value = true
                } else {
                    value = it
                    onValueChange(it)
                    if (it.isNotEmpty())
                        showIcon.value = true
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
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium.copy(labelColor)
                )
            },
            shape = RoundedCornerShape(20.dp),
            visualTransformation = if (isPassword && showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
            supportingText = {
                if (!error?.trim().isNullOrEmpty())
                    SubHeadingText(text = error.toString(), color = Alert, TextAlign.Start)
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
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    tint = labelColor
                )

            },
            keyboardOptions = if (isPassword) {
                KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                )
            } else
                keyboardOptions,
        )
    }
}

@Composable
fun SocialMediaButton(icon: Int, onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() }, modifier = Modifier.size(70.dp), border =
        BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null, Modifier.size(30.dp)
        )
    }
}

@Composable
fun ClickableText(label: String, labelColor: Color = PurplePlum, onClick: () -> Unit) {
    TextButton(onClick = { onClick() }) {
        SubHeadingText(text = label, color = labelColor)
    }
}

@Composable
fun CustomCheckbox(checkBox: MutableState<Boolean>, interactionSource: (Boolean) -> Unit) {
    Checkbox(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.DarkGray),
                RoundedCornerShape(5.dp)
            )
            .padding(4.dp)
            .size(16.dp),
        checked = checkBox.value,
        onCheckedChange = {
            checkBox.value = it
            interactionSource(it)
        },
        colors = CheckboxDefaults.colors(
            checkmarkColor = PurplePlum,
            checkedColor = Color.Transparent,
            uncheckedColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordValidationComponent(label: String, isValid: Boolean) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        if (isValid)
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Unspecified
            ) else
            Icon(
                painter = painterResource(id = R.drawable.no_data),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Alert
            )
        Spacer(modifier = Modifier.width(8.dp))
        SubHeadingText(
            text = label,
            color = Color.DarkGray,
            TextAlign.Justify
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
//    RectanglePrimaryButton(label = "Let’s start") {
//
//    }
//    HeadlineLarge(text = "Welcome to\nOneLook")
//    SubHeadingText(text = "Just take a look and take action!")
    TextInputWithLabel(
        Modifier.padding(horizontal = 24.dp),
        "Full Name",
        labelColor = Color.DarkGray,
        default = "",
        isPassword = true,
        error = null, leadingIcon = R.drawable.hide_password
    ) {}
}