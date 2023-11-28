
package com.fanimo.ecommerce.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesEleDatabase(
        @ApplicationContext context: Context,
    ): EleDatabase = Room.databaseBuilder(
        context,
        EleDatabase::class.java,
        "ele-database",
    ).build()
}
