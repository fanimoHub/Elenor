package com.fanimo.ecommerce.elenor.feature.account.auth.screens

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fanimo.ecommerce.elenor.feature.account.R
import com.fanimo.ecommerce.elenor.feature.account.auth.components.OtpInputField
import com.fanimo.ecommerce.elenor.feature.account.auth.utils.OtpBroadcastReceiver
import com.fanimo.ecommerce.elenor.feature.account.auth.utils.startSMSRetrieverClient
import com.google.android.gms.auth.api.phone.SmsRetriever

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    onHomeClick: () -> Unit,
    phoneNumber: String,
    loginUser: () -> Unit,
    setOtpValue: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    ) {
    val context = LocalContext.current
    var otpValue by remember { mutableStateOf("") }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current


    OtpReceiverEffect(
        context = context,
        onOtpReceived = { otp ->
            otpValue = otp
            if (otpValue.length == 6) {
                keyboardController?.hide()
                isOtpFilled = true
                setOtpValue(otpValue)
                loginUser()
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    /**
     * Set status bar color for this screen
     */
    (LocalView.current.context as Activity).window.statusBarColor = Color.White.toArgb()

    /**
     * OTP Screen UI starts here
     */
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithContent {
                        drawContent()
                    },
                navigationIcon = {
                    Box(
                        Modifier
                            .size(48.dp)
//                            .clickable { navController.popBackStack() }
                            )
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            tint = Color.DarkGray,
                            contentDescription = "Back",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.DarkGray,
                    actionIconContentColor = Color.DarkGray
                ),
                title = { Text(text = "Enter One Time Password") },
                windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
            )
        },
        bottomBar = {
            Button(
                onClick = {},
                enabled = isOtpFilled,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
            ) {
                Text(text = "Continue")
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 0.dp),
                    text = "Please verify your phone number with the OTP we sent to $phoneNumber",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(24.dp),
                    color = Color.White
                ) {
                    OtpInputField(
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .focusRequester(focusRequester),
                        otpText = otpValue,
                        shouldCursorBlink = false,
                        onOtpModified = { value, otpFilled ->
                            otpValue = value
                            isOtpFilled = otpFilled
                            if (otpFilled) {
                                keyboardController?.hide()
                            }
                        }
                    )
                }
            }
        }
    }

}

//@SuppressLint("UnspecifiedRegisterReceiverFlag")
@Composable
fun OtpReceiverEffect(
    context: Context,
    onOtpReceived:  (String) -> Unit
    ) {
//    val otpReceiver = remember { OTPReceiver() }
    startSMSRetrieverClient(context)
    OtpBroadcastReceiver(
        context = context,
        SmsRetriever.SMS_RETRIEVED_ACTION,
        onOtpReceived,
    )

}
