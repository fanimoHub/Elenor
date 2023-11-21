package com.fanimo.ecommerce.elenor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import com.fanimo.ecommerce.designsystem.theme.EleTheme
import com.fanimo.ecommerce.elenor.ui.EleApp
import com.fanimo.ecommerce.elenor.MainActivityUiState.Loading
import dagger.hilt.android.AndroidEntryPoint

//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainActivityViewModel by viewModels()
      @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
      override fun onCreate(savedInstanceState: Bundle?) {
          //val splashScreen = installSplashScreen()

          super.onCreate(savedInstanceState)

          val uiState: MainActivityUiState by mutableStateOf(Loading)

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
