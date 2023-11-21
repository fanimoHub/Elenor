package com.fanimo.ecommerce.elenor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanimo.ecommerce.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.fanimo.ecommerce.elenor.MainActivityUiState.Success
import com.fanimo.ecommerce.elenor.MainActivityUiState.Loading
import com.fanimo.ecommerce.core.data.repository.UserDataRepository

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    userDataRepository: UserDataRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = userDataRepository.userData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    data class Success(val userData: UserData) : MainActivityUiState
}