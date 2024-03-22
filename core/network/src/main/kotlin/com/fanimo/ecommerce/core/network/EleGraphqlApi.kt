package com.fanimo.ecommerce.core.network

import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

class EleGraphqlApi {

    fun getApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }

        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:8000/graphql/")
            .okHttpClient(okHttpClient)
            .build()
    }

}
