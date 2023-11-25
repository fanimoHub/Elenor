package com.fanimo.ecommerce.elenor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.fanimo.ecommerce.designsystem.theme.EleTheme
import com.fanimo.ecommerce.elenor.ui.EleApp
import com.fanimo.ecommerce.elenor.MainActivityUiState.Loading
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import com.fanimo.ecommerce.elenor.MainActivityUiState.Success



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainActivityViewModel by viewModels()
      @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
      override fun onCreate(savedInstanceState: Bundle?) {
          val splashScreen = installSplashScreen()

          super.onCreate(savedInstanceState)

          var uiState: MainActivityUiState by mutableStateOf(Loading)


          // Update the uiState
          lifecycleScope.launch {
              lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                  viewModel.uiState
                      .onEach {
                          uiState = it
                      }
                      .collect()
              }
          }

          // Keep the splash screen on-screen until the UI state is loaded. This condition is
          // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
          // the UI.
          splashScreen.setKeepOnScreenCondition {
              when (uiState) {
                  Loading -> true
                  is Success -> false
              }
          }

          // Turn off the decor fitting system windows, which allows us to handle insets,
          // including IME animations, and go edge-to-edge
          // This also sets up the initial system bar style based on the platform theme
          //enableEdgeToEdge()


          setContent {
            EleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    EleApp(
                        windowSizeClass = calculateWindowSizeClass(this),
                    )

                }
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EleTheme {
        EleApp()
    }
}*/
