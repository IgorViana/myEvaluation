package com.tribo_mkt.evaluation.network.retrofit

import com.tribo_mkt.evaluation.network.services.EvaluationService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private var INSTANCE: Retrofit? = null

        private fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE as Retrofit
        }
    }

    val evaluationService: EvaluationService = getInstance().create(
        EvaluationService::class.java
    )
}