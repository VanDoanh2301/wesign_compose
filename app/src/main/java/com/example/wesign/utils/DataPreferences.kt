package com.example.wesign.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "my_data_store")

class DataPreferences @Inject constructor(@ApplicationContext  context: Context) {

    private val appContext = context.applicationContext
    private val gson = Gson()
    private val keyStoreManager = KeyStoreManager(appContext)


    suspend fun saveEncryptedString(key: String, value: Any) {
        keyStoreManager.createKey(KeyStoreManager.ALIAS_TOKEN)
        val dataStoreKey = stringPreferencesKey(key)
        val encryptedValue = keyStoreManager.encryptAny(value, KeyStoreManager.ALIAS_TOKEN)
        appContext.dataStore.edit { preferences ->
            preferences[dataStoreKey] = encryptedValue
        }
    }


    fun <T> getDecryptedString(key: String, classType: Class<T>): Flow<T?> {
        val dataStoreKey = stringPreferencesKey(key)
        return appContext.dataStore.data.map { preferences ->
            val encryptedValue = preferences[dataStoreKey] ?: ""
            if (encryptedValue.isNotEmpty()) {
                 keyStoreManager.decryptAny(encryptedValue, KeyStoreManager.ALIAS_TOKEN, classType)
            } else {
                null
            }
        }
    }

    suspend fun getToken(): String? {
        return getDecryptedString(TOKEN_KEY, String::class.java).firstOrNull()
    }

    suspend fun deleteString(key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        appContext.dataStore.edit { preferences ->
            preferences.remove(dataStoreKey)
        }
    }


}

const val TOKEN_KEY = "token_key"