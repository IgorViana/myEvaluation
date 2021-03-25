package com.tribo_mkt.evaluation.application

import android.app.Application
import com.tribo_mkt.evaluation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyEvaluationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyEvaluationApplication)
            koin.loadModules(listOf(appModule))
            koin.createRootScope()
        }
    }
}