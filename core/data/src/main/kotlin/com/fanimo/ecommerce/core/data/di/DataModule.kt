package com.fanimo.ecommerce.core.data.di

import com.fanimo.ecommerce.core.data.repository.OfflineFirstUserDataRepository
import com.fanimo.ecommerce.core.data.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository
}