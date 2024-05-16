package com.fanimo.ecommerce.elenor.feature.account.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fanimo.ecommerce.elenor.feature.account.auth.screens.LoginScreen
import com.fanimo.ecommerce.elenor.feature.account.auth.screens.OtpScreen

@Composable
internal fun AuthRoute(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),

    ) {
    val phoneNumber by  remember {viewModel.phoneNumber}
    var isPhoneNumberFilled by remember { mutableStateOf(false) }
    val setPhoneNumber = viewModel::setPhoneNumber

    if (!isPhoneNumberFilled) {
                LoginScreen(
                    onPhoneNumberEntered = { value ->
                        setPhoneNumber(value)
                        if(value.length==11){
                            isPhoneNumberFilled = true
                        }
                    }
                )
    }else{
        OtpScreen(phoneNumber.toString())
    }
}