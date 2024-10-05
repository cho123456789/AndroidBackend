package com.example.backendtest.Common

import com.example.backendtest.domain.TextGetRespository
import com.example.backendtest.domain.TextUpdateRespository
import com.example.backendtest.remote.TextService
import com.example.backendtest.remote.repository.TextGetRespositoryImpl
import com.example.backendtest.remote.repository.TextUpdateRespositoryImpl
import com.example.backendtest.usecase.GetUserTextUseCase
import com.example.backendtest.usecase.UpdateUserTextUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTextGetInfoRepository(api : TextService): TextGetRespository {
        return TextGetRespositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTextUpdateInfoRepository(api : TextService): TextUpdateRespository {
        return TextUpdateRespositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideGetTextInfoUseCase(repository: TextGetRespository): GetUserTextUseCase {
        return GetUserTextUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTextInfoUseCase(repository: TextUpdateRespository): UpdateUserTextUseCase {
        return UpdateUserTextUseCase(repository)
    }
}