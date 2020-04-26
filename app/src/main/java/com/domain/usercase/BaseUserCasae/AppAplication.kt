package com.domain.usercase.BaseUserCasae

import android.app.Application
import com.domain.Koin.di.listModules
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.startKoin

class AppAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(modules = listModules, context = this)
    }

}