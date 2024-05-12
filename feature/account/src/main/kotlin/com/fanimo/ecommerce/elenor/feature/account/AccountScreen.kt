package com.fanimo.ecommerce.elenor.feature.account


import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.fanimo.ecommerce.elenor.feature.account.auth.screens.LoginScreen

@Composable
internal fun AccountRoute(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    AccountScreen(
        onHomeClick = onHomeClick,
        modifier = modifier,

        )
}


@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun AccountScreen(
    onHomeClick: ()->Unit,
    modifier: Modifier = Modifier,
){
    Column {
//        Text(text = "This is Product Screen",
//            modifier = modifier,
//        )
//        Button(onClick = onHomeClick) {
//            Text(text = "Home")
//
//
//        }
        var phoneNumber by remember { mutableStateOf("") }
        LoginScreen(
            onPhoneNumberEntered = { phoneNumber = it}
        )
        Spacer(modifier = Modifier)
        Text(text = phoneNumber)

//        OtpScreen()
    }
}




