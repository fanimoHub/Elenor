package com.fanimo.ecommerce.elenor.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import com.fanimo.ecommerce.elenor.navigation.EleNavHost

@Composable
fun EleApp(windowSizeClass: WindowSizeClass,
           appState: EleAppState = rememberEleAppState(
                windowSizeClass = windowSizeClass,
            )
){
    Column {
        Text(text = "Hello")
        EleNavHost(appState = appState)
    }

}