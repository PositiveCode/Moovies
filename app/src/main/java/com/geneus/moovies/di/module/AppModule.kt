package com.geneus.moovies.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.geneus.moovies.BuildConfig
import com.geneus.moovies.BuildConfig.BASE_URL
import com.geneus.moovies.data.api.ApiHelper
import com.geneus.moovies.data.api.ApiHelperImpl
import com.geneus.moovies.data.api.ApiService
import com.geneus.moovies.data.api.exception.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient(androidContext()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideOkHttpClient(context: Context) = if (BuildConfig.DEBUG) {
    val chuckerInterceptor = ChuckerInterceptor.Builder(context).build()
    val loggingInterceptor = HttpLoggingInterceptor()

    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(chuckerInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)