
package com.fanimo.ecommerce.core.network.di

import com.fanimo.ecommerce.core.network.fake.FakeEleNetworkDataSource
import com.fanimo.ecommerce.core.network.EleNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: FakeEleNetworkDataSource): EleNetworkDataSource
}
