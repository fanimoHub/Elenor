package com.fanimo.ecommerce.elenor.feature.account.auth.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.auth.api.phone.SmsRetrieverClient
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import java.util.regex.Matcher
import java.util.regex.Pattern


@Composable
fun OtpBroadcastReceiver(
    context: Context,
    systemAction: String,
    onSystemEvent:  (otpValue: String) -> Unit
) {

    // Safely use the latest onSystemEvent lambda passed to the function
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    // If either context or systemAction changes, unregister and register again
    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent != null) {
                    if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                        val extras = intent.extras
//                      val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
                        val status = when {
                            SDK_INT >= 33 -> intent.getParcelableExtra(SmsRetriever.EXTRA_STATUS, Status::class.java)
                            else -> @Suppress("DEPRECATION") (intent.getParcelableExtra(SmsRetriever.EXTRA_STATUS))
                        }

                        Log.e("fani", "onReceive")
                        if (status != null) {
                            when (status.statusCode) {
                                CommonStatusCodes.SUCCESS -> {
                                    Log.e("fani", "SUCCESS")
                                    val message = extras?.getString(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                                    Log.e("fani", "message1$message")

                                    val otpValue = extractOtpFromMessage(message)
                                    if (otpValue != null) {
                                        Log.e("SMS", otpValue)
                                        currentOnSystemEvent(otpValue)
                                    }
                                }

                                else -> {
                                    // Handle other status codes
                                }
                            }
                        }
                    }
                }
            }
        }

        if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(
                broadcast,
                intentFilter,
                Context.RECEIVER_EXPORTED
            )
        }else{
            context.registerReceiver(
                broadcast,
                intentFilter,
            )}

        // When the effect leaves the Composition, remove the callback
        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}


private fun extractOtpFromMessage(message: String): String? {
    val p: Pattern = Pattern.compile("(|^)\\d{6}")
    val m: Matcher = p.matcher(message)
    if (m.find()) {
        return m.group(0)
    }
    return null
}


fun startSMSRetrieverClient(context: Context) {
    val client: SmsRetrieverClient = SmsRetriever.getClient(context)
    val smsRetrieverTask = client.startSmsRetriever()
    smsRetrieverTask.addOnSuccessListener {
        Log.e("OTPReceiver", "startSMSRetrieverClient addOnSuccessListener")
    }
    smsRetrieverTask.addOnFailureListener { e ->
        Log.e("OTPReceiver", "startSMSRetrieverClient addOnFailureListener" + e.stackTrace)
    }
}