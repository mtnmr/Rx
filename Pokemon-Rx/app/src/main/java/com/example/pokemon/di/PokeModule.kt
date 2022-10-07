package com.example.pokemon.di

import com.example.pokemon.api.BASE_URL
import com.example.pokemon.api.PokeApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokeModule {

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providePokeApiService(retrofit :Retrofit) : PokeApiService{
        return retrofit.create(PokeApiService::class.java)
    }
}