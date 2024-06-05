package com.fanimo.ecommerce.elenor.feature.account.auth

import android.util.Log
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.fanimo.ecommerce.core.data.repository.UserDataRepository
import com.fanimo.ecommerce.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
): ViewModel() {

//    val uiState: StateFlow<AuthUiState> = userDataRepository.userData.map {
//        AuthUiState.Success(it)
//    }.stateIn(
//        scope = viewModelScope,
//        initialValue = AuthUiState.Loading,
//        started = SharingStarted.WhileSubscribed(5_000)
//    )
private val isLoggedIn: Flow<Boolean> =
    userDataRepository.userData.map { it.isLoggedIn }

    val loginUiState: StateFlow<LoginUiState> = isLoggedIn.map { isLoggedIn ->

        if (isLoggedIn) {
            LoginUiState.LoggedIn
        } else {
            LoginUiState.NotLoggedIn
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LoginUiState.Loading,
        )


    private val _phoneNumber = mutableStateOf(TextFieldState())
    val phoneNumber: State<TextFieldState> = _phoneNumber

    fun setPhoneNumber(value:String){
        _phoneNumber.value = phoneNumber.value.copy(text = value)
    }

    private val _otpValue = mutableStateOf(TextFieldState())
    private val otpValue: State<TextFieldState> = _otpValue

    fun setOtpValue(value:String){
        _otpValue.value = otpValue.value.copy(text = value)
    }

    private val _firstName = mutableStateOf(TextFieldState())
    val firstName: State<TextFieldState> = _firstName

    fun setFirstName(value:String){
        _firstName.value = firstName.value.copy(text = value)
    }

    private val _lastName = mutableStateOf(TextFieldState())
    val lastName: State<TextFieldState> = _lastName

    fun setLastName(value:String){
        _lastName.value = lastName.value.copy(text = value)
    }

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value:String){
        _passwordState.value = passwordState.value.copy(text = value)
    }

    fun loginUser(){
        viewModelScope.launch {
//            requestOtp()
//            verifyOtp()
            userDataRepository.setIsLoggedIn(true)
            delay(100)



            Log.d("My isLogin afterAuth", loginUiState.value.toString())




//            when(loginResult.result){
//                is Resource.Success->{
//                    _eventFlow.emit(
//                       UiEvents.NavigateEvent(HomeScreenDestination.route)
//                    )
//                }
//                is Resource.Error->{
//                    UiEvents.SnackbarEvent(
//                        loginResult.result.message ?: "Error!"
//                    )
//                }
//                else -> {
//
//                }
//            }
        }
    }

    private fun requestOtp(){

    }
    private fun verifyOtp(){
        val nnn = otpValue
    }

    fun registerUser(){
        viewModelScope.launch {





//            when(registerResult.result){
//                is Resource.Success->{
//                    _eventFlow.emit(
//                        UiEvents.NavigateEvent(HomeScreenDestination.route)
//                    )
//                }
//                is Resource.Error->{
//                    UiEvents.SnackbarEvent(
//                        registerResult.result.message ?: "Error!"
//                    )
//                }
//                else -> {
//
//                }
//            }
        }
    }

}



data class TextFieldState(
    val text :  String = "",
    val error: String? = null
)

sealed interface LoginUiState {
    data object Loading : LoginUiState  // isLoggedIn = unknown
    data object NotLoggedIn: LoginUiState // isLoggedIn = false
    data object LoggedIn : LoginUiState // isLoggedIn = true
}
