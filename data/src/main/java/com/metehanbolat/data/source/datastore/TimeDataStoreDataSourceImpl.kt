package com.metehanbolat.data.source.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class TimeDataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TimeDataStoreDataSource {

    private object PreferenceKeys {
        val serviceCallTime = stringPreferencesKey("service_call_time")
    }

    override suspend fun saveToDataStore(serviceCallTime: String) {
        try {
            dataStore.edit { preference ->
                preference[PreferenceKeys.serviceCallTime] = serviceCallTime
            }
        } catch (e: Exception) {
            Log.e("DataStore", e.message.toString())
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