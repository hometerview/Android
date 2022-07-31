package com.ftw.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreProvider(private val dataStore: DataStore<Preferences>) {


    fun <T> getValue(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.catch { emit(emptyPreferences()) }
            .map { preference -> preference[key] }
    }

    suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

}