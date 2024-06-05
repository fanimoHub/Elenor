package com.fanimo.ecommerce.elenor.feature.account.auth

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fanimo.ecommerce.elenor.feature.account.auth.screens.LoginScreen
import com.fanimo.ecommerce.elenor.feature.account.auth.screens.OtpScreen

@Composable
internal fun AuthRoute(
    onHomeClick: () -> Unit,
    popBack: () -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),

    ) {
    val phoneNumber by remember { viewModel.phoneNumber }
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    //Rahim
//    var isPhoneNumberValid by remember { mutableStateOf(false) }
    val setPhoneNumber = viewModel::setPhoneNumber
//Rahim
    if (phoneNumber.text.length<11) {
        LoginScreen(
            onPhoneNumberEntered = { phoneNumberInput ->
                if (phoneNumberInput.length == 11) {
                    setPhoneNumber(phoneNumberInput)
                } else {
                    // Display an error message for invalid phone number length
                }
            },
            modifier = modifier,
        )
    } else {
        OtpScreen(
            onHomeClick,
            phoneNumber.toString(),
            loginUser = viewModel::loginUser,
            setOtpValue = viewModel::setOtpValue,
            modifier = modifier,
        )
    }
    //Rahim
    when (loginUiState) {
        is LoginUiState.Loading -> {
           Log.d("login","loading")
        }

        is LoginUiState.LoggedIn -> {
            popBack()
        }

        is LoginUiState.NotLoggedIn -> {
            Log.d("login","NotLoggedIn")
        }
    }
}