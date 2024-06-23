package com.fanimo.ecommerce.elenor.feature.account


import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
internal fun AccountRoute(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    AccountScreen()
}



@Composable
fun AccountScreen() {
    val context = LocalContext.current
//    val currentUser =

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(69.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {

                Image(
                    modifier = Modifier.size(65.dp),
//                    currentUser.photoUrl
                    painter = rememberAsyncImagePainter(model = "currentUser.photoUrl"),
                    contentDescription = "user image",
                    contentScale = ContentScale.Fit
                )

            Column {
                Row(modifier = Modifier.padding(top = 8.dp)) {
//                    currentUser?.displayName ?:
                    Text(text =  "Name", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff181725),
                        lineHeight = 18.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = FontFamily(Font(R.font.gilroybold, weight = FontWeight.Bold)),
                        textAlign = TextAlign.Start
                    )
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(20.dp),
                        onClick = {
                            Toast
                                .makeText(context, "Editing me", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(0.67f),
                            painter = painterResource(id = R.drawable.edit_icon),
                            contentDescription = "edit username",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
//                currentUser?.email ?:
                Text(text =  "name@gmail.com",
                    style = MaterialTheme.typography.headlineSmall)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFB3B3B3)
        )

        AccountNavItem(icon = R.drawable.orders, name = "Orders")
        AccountNavItem(icon = R.drawable.my_details, name = "My Details")
        AccountNavItem(icon = R.drawable.address, name = "Delivery Address")
        AccountNavItem(icon = R.drawable.payment, name = "Payment methods")
        AccountNavItem(icon = R.drawable.promo, name = "Promo Card")
        AccountNavItem(icon = R.drawable.notifications, name = "Notifications")
        AccountNavItem(icon = R.drawable.help, name = "Help")
        AccountNavItem(icon = R.drawable.about, name = "About")
        AccountNavItem(icon = R.drawable.orders, name = "Orders")
        AccountNavItem(icon = R.drawable.my_details, name = "My Details")
        AccountNavItem(icon = R.drawable.address, name = "Delivery Address")
        AccountNavItem(icon = R.drawable.payment, name = "Payment methods")
        AccountNavItem(icon = R.drawable.promo, name = "Promo Card")
        AccountNavItem(icon = R.drawable.notifications, name = "Notifications")
        AccountNavItem(icon = R.drawable.help, name = "Help")
        AccountNavItem(icon = R.drawable.about, name = "About")


        Spacer(modifier = Modifier.height(26.dp))

        Button(
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                onsignOut()
                Toast
                    .makeText(context, "Logging you out", Toast.LENGTH_SHORT)
                    .show()
//                navController.navigate(Screen.Start.route) {
//                    popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
//                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF2F3F2),
                contentColor = Color(0xFF53B175)
            ),
            shape = RoundedCornerShape(19),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.log_out),
                    contentDescription = "log out",
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.padding(start = 100.dp),
                    text = "Log Out",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xff181725),
                        lineHeight = 18.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)),
                        textAlign = TextAlign.Start
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

fun onsignOut() {
    TODO("Not yet implemented")
}

//@ExperimentalMaterialApi
@Composable
fun AccountNavItem(@DrawableRes icon: Int, name: String) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth(), onClick = {
        Toast
            .makeText(context, "$name clicked", Toast.LENGTH_SHORT)
            .show()
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    modifier = Modifier.height(20.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = name, style = MaterialTheme.typography.bodySmall)
            }
            Image(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = "forward"
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color(0xFFB3B3B3)
    )

}


