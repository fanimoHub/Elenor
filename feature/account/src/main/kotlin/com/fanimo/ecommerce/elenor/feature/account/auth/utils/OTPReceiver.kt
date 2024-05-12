package com.fanimo.ecommerce.elenor.feature.account.auth.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
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

/**
 * BroadcastReceiver to wait for SMS messages. This can be registered either
 * in the AndroidManifest or at runtime. Should filter Intents on
 * SmsRetriever.SMS_RETRIEVED_ACTION.
 *
 * TODO: Working in Android 13 (API 33) and 14 (API 34), but not working in Android 12 (API 31)
 *
 */


@Composable
fun OtpBroadcastReceiver(
    context: Context,
    systemAction: String,
    onSystemEvent: (otpValue: String) -> Unit
) {
    // Grab the current context in this part of the UI tree
//    val context = LocalContext.current

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
                        val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
                        Log.e("dani", "onReceive")
                        when (status.statusCode) {
                            CommonStatusCodes.SUCCESS -> {
                                Log.e("dani", "SUCCESS")
                                val message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                                Log.e("dani", "message1$message")

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

//        context.registerReceiver(broadcast, intentFilter)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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




//class OTPReceiver : BroadcastReceiver() {
//    private var otpReceiveListener: OTPReceiveListener? = null
//
//    fun init(otpReceiveListener: OTPReceiveListener?) {
//        this.otpReceiveListener = otpReceiveListener
//    }
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
//            val extras: Bundle? = intent.extras
//            val status: Status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
//
//            when (status.statusCode) {
//                CommonStatusCodes.SUCCESS -> {
//                    // Get SMS message contents
//                    val msg = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE) as String
//                    Log.e("OTPReceiver", "SMS Received in OTPReceiver: $msg")
//
//                    // extract the 6-digit code from the SMS
//                    val smsCode = msg.let { "[0-9]{6}".toRegex().find(it) }
//                    Log.e("OTPReceiver", "OTP fetched from SMS in OTPReceiver: $smsCode")
//
//                    smsCode?.value?.let { otpReceiveListener?.onOTPReceived(it) }
//                }
//
//                CommonStatusCodes.TIMEOUT -> {
//                    otpReceiveListener?.onOTPTimeOut()
//                }
//            }
//        }
//    }
//
//    interface OTPReceiveListener {
//        fun onOTPReceived(otp: String?)
//        fun onOTPTimeOut()
//    }
//}

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