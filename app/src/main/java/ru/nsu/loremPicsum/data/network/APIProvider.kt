package ru.nsu.loremPicsum.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIProvider {
    val loremPicsumAPI: LoremPicsumAPI by lazy {
        makeRxApiBuilder(LoremPicsumAPI.baseUrl)
                .client(OkHttpClient().newBuilder().addInterceptor(loggingInterceptor()).build())
                .build()
                .create(LoremPicsumAPI::class.java)
    }

    @Suppress("SameParameterValue")
    private fun makeRxApiBuilder(baseUrl: String): Retrofit.Builder {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}