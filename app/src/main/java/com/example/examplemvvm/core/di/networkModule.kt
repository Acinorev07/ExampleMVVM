package com.example.examplemvvm.core.di

import com.example.examplemvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object networkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://lakshmi-e8965-default-rtdb.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit:Retrofit): QuoteApiClient {
        return retrofit.create(QuoteApiClient::class.java)
    }
}