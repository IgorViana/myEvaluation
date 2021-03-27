package com.tribo_mkt.evaluation.di

import com.tribo_mkt.evaluation.network.retrofit.RetrofitBuilder
import com.tribo_mkt.evaluation.network.services.EvaluationService
import com.tribo_mkt.evaluation.repository.EvaluationRepository
import com.tribo_mkt.evaluation.ui.AlbunsFragment.AlbunsViewModel
import com.tribo_mkt.evaluation.ui.FotosFragment.FotosViewModel
import com.tribo_mkt.evaluation.ui.InicioFragment.InicioViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<Retrofit> { RetrofitBuilder.getInstance() }
    single<EvaluationService> { RetrofitBuilder().evaluationService }

    factory<EvaluationRepository> { EvaluationRepository(get()) }

    viewModel { InicioViewModel(get()) }
    viewModel { AlbunsViewModel(get()) }
    viewModel { FotosViewModel(get()) }
}