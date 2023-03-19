package com.sisk.appoint.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sisk.appoint.di.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenRepository(private val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")

        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    val token: Flow<String?> = context.dataStore.data.map {pref ->
            pref[TOKEN_KEY]
        }
    val refreshToken: Flow<String?> = context.dataStore.data.map {pref ->
        pref[REFRESH_TOKEN_KEY]
    }

    suspend fun saveToken(token: String){
        context.dataStore.edit {pref ->
            pref[TOKEN_KEY] = token

        }
    }

    suspend fun saveRefreshToken(token: String){
        context.dataStore.edit {pref ->
            pref[REFRESH_TOKEN_KEY] = token
        }
    }

    suspend fun deleteToken(){
        context.dataStore.edit {
            it.remove(TOKEN_KEY)
        }
    }

    suspend fun deleteRefreshToken(){
        context.dataStore.edit {
            it.remove(REFRESH_TOKEN_KEY)
        }
    }

}