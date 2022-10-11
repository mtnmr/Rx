package com.example.pokemon.api

import com.example.pokemon.model.Pokemon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://pokeapi.co/api/v2/"

interface PokeApiService {
//    @GET("pokemon/{id}/")
//    fun getPokemon(@Path("id") id:String) : Single<Response<Pokemon>>

    @GET("pokemon/{id}/")
    fun getPokemon(@Path("id") id:String) : Observable<Pokemon>
}