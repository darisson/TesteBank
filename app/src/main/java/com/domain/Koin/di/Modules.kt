package com.domain.Koin.di

import com.data.repository.AuthRepository
import com.data.repository.HomeRepository
import com.data.source.local.SharedPreferencesManager
import com.data.source.remote.retrofit.RetrofitConfiguration
import com.domain.usercase.HomeUserCase.HomeUserCase
import com.domain.usercase.loginUserCase.LoginUserCase
import com.presentation.ui.home.viewmolde.HomeViewModel
import com.presentation.ui.login.viewmodel.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val listModules = listOf(
    module {
        single { RetrofitConfiguration().getAppRequest()}
        single { SharedPreferencesManager(androidApplication()) }

        factory { AuthRepository(get()) }
        single { LoginUserCase(get()) }
        viewModel { LoginViewModel(get(), get()) }

        factory { HomeRepository(get()) }
        single { HomeUserCase(get()) }
        viewModel { HomeViewModel(get(), get()) }
    }
)

