package com.example.wesign.domain.di

import com.example.wesign.data.remote.ApiUser
import com.example.wesign.data.repository.UserRepositoryImpl
import com.example.wesign.domain.repository.UserRepository
import com.example.wesign.domain.usecase.user.login_use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideUserImpl(mUser: ApiUser): UserRepository {
        return UserRepositoryImpl(mUser)
    }

}