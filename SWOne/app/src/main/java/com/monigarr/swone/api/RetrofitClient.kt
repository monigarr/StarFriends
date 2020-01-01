package com.monigarr.swone.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitClient {

    private constructor() {}

    companion object {
        private var retrofit: Retrofit? = null
        fun getAPIService(): SwapiService? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createAsync()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://swapi.co/api/")
                    .build()
            }
            return retrofit?.create(SwapiService::class.java)
        }
    }
}