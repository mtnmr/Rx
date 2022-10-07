package com.example.pokemon.api

import com.example.pokemon.model.Pokemon
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokeRepository @Inject constructor(private val pokeApiService: PokeApiService) {

    fun getPoke(id : String) : Single<Pokemon> =
        pokeApiService.getPokemon(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.body()
            }


//    companion object{
//        @Volatile
//        private var INSTANCE:PokeRepository ?= null
//
//        fun getRepository():PokeRepository{
//            return INSTANCE ?: synchronized(this){
//                val instance = PokeRepository()
//                INSTANCE = instance
//
//                instance
//            }
//        }
//    }
}