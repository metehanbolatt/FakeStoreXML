package com.metehanbolat.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.metehanbolat.data.di.coroutine.IoDispatcher
import com.metehanbolat.domain.repository.DataStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): DataStoreRepository {

    private object PreferenceKeys {
        val serviceCallTime = stringPreferencesKey("service_call_time")
    }

    override suspend fun saveToDataStore(serviceCallTime: String) {
        withContext(ioDispatcher) {
            try {
                dataStore.edit { preference ->
                    preference[PreferenceKeys.serviceCallTime] = serviceCallTime
                }
            } catch (e: Exception) {
                Log.e("DataStore", e.message.toString())
            }
        }
    }

    override val readFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            preference[PreferenceKeys.serviceCallTime] ?: ""
        }
}