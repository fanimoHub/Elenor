package com.fanimo.ecommerce.elenor.feature.home

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun HomeRoute(
    onProductClick: () -> Unit,
    modifier: Modifier = Modifier,

) {

    HomeScreen(
        onProductClick = onProductClick,
        modifier = modifier,
    )
}


@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun HomeScreen(
    onProductClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column {
        Text(text = "This is Home Screen",
            modifier = modifier,
        )
        Button(onClick = onProductClick) {
            Text(text = "Product")
        }
    }


}