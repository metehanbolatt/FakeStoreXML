package com.metehanbolat.data.di.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.metehanbolat.data.constants.TIME_PREFERENCES
import com.metehanbolat.data.di.coroutine.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

@InstallIn(ViewModelComponent::class)
@Module
object DataStoreModule {

    @ViewModelScoped
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, TIME_PREFERENCES)),
            scope = CoroutineScope(ioDispatcher),
            produceFile = { appContext.preferencesDataStoreFile(TIME_PREFERENCES) }
        )
}