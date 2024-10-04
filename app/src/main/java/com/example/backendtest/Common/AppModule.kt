package com.example.backendtest.Common

import com.example.backendtest.Common.Constants.BaseUrl
import com.example.backendtest.remote.TextService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Install this module in the SingletonComponent
class AppModule {

    @Provides
    @Singleton
    fun provideTextApiService(): TextService {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TextService::class.java)
    }
}
