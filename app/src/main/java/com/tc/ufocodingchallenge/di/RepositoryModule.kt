package com.tc.ufocodingchallenge.di

import com.tc.ufocodingchallenge.data.repoImpl.UfoRepositoryImpl
import com.tc.ufocodingchallenge.domain.repository.UfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideUfoRepository( ufoRepositoryImpl: UfoRepositoryImpl): UfoRepository
}