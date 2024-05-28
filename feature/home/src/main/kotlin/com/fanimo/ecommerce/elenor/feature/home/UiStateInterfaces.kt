/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fanimo.ecommerce.elenor.feature.home

import com.fanimo.ecommerce.core.model.data.FollowableTopic
import com.fanimo.ecommerce.core.model.data.UserData

/**
 * A sealed hierarchy describing the onboarding state for the for you screen.
 */
sealed interface OnboardingUiState {
    /**
     * The onboarding state is loading.
     */
    data object Loading : OnboardingUiState

    /**
     * The onboarding state was unable to load.
     */
    data object LoadFailed : OnboardingUiState

    /**
     * There is no onboarding state.
     */
    data object NotShown : OnboardingUiState

    /**
     * There is a onboarding state, with the given lists of topics.
     */
    data class Shown(
        val topics: List<FollowableTopic>,
    ) : OnboardingUiState {
        /**
         * True if the onboarding can be dismissed.
         */
        val isDismissable: Boolean get() = topics.any { it.isFollowed }
    }
}

sealed interface UserUiState {
    data object Loading : UserUiState  // isLoggedIn = unknown
    data object NotLoggedIn: UserUiState // isLoggedIn = false
    data class Success(val userData: UserData) : UserUiState // isLoggedIn = true
}

sealed interface LoginUiState {
    data object Loading : LoginUiState  // isLoggedIn = unknown
    data object NotLoggedIn: LoginUiState // isLoggedIn = false
    data object LoggedIn : LoginUiState // isLoggedIn = true
}