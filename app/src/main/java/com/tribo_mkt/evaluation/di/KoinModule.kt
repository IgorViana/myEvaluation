package com.tribo_mkt.evaluation.di

import com.tribo_mkt.evaluation.network.retrofit.RetrofitBuilder
import com.tribo_mkt.evaluation.network.services.EvaluationService
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<Retrofit> { RetrofitBuilder.getInstance() }
    single<EvaluationService> { RetrofitBuilder().evaluationService }
}