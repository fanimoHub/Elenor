package com.fanimo.ecommerce.core.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.DefaultHttpEngine

val apolloClient = ApolloClient.Builder()
    .httpEngine(DefaultHttpEngine(timeoutMillis = 180000))
    .serverUrl("http://10.0.2.2:8000/graphql/")
    .build()
