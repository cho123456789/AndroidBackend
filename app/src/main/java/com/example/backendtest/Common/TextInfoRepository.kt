package com.example.backendtest.Common

import com.example.backendtest.domain.TextRespository
import com.example.backendtest.remote.TextService
import com.example.backendtest.remote.repository.TextRespositoryImpl
import com.example.backendtest.usecase.GetUserTextUseCase
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
    fun provideCharacterInfoRepository(api : TextService): TextRespository {
        return TextRespositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideGetTextInfoUseCase(repository: TextRespository): GetUserTextUseCase {
        return GetUserTextUseCase(repository)
    }
}
