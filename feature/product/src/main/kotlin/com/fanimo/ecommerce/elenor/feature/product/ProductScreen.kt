package com.fanimo.ecommerce.elenor.feature.product

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ProductRoute(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {

    ProductScreen(
        onHomeClick = onHomeClick,

        modifier = modifier,

        )
}


@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun ProductScreen(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column {
        Text(text = "This is Product Screen",
            modifier = modifier,
        )
        Button(onClick = onHomeClick) {
            Text(text = "Home")
        }
    }

}