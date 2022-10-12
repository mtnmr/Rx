package com.example.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.api.PokeRepository
import com.example.pokemon.model.PokeImage
import com.example.pokemon.model.PokeSprite
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.SpritesOther
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(private val repository: PokeRepository):ViewModel() {

//    val pokemon:PublishSubject<Pokemon> = PublishSubject.create()
//
//    fun getPoke(id:String){
//        repository.getPoke(id)
//            .subscribe{ poke ->
//                pokemon.onNext(poke)
//            }
//    }

    fun getPoke(id:String):Observable<Pokemon>{
        return repository.getPoke(id)
    }

    private var _currentPokemon = MutableLiveData(Pokemon(
        id = 0, name = "sample", height = 0, weight = 0, types = listOf(),
        sprites = PokeSprite("","","","", SpritesOther(PokeImage("")))
    ))
    val currentPokemon:LiveData<Pokemon> = _currentPokemon

    fun changePokemon(pokemon: Pokemon){
        _currentPokemon.value = pokemon
    }

    fun backPokeId():String{
        return currentPokemon.value?.id?.minus(1).toString()
    }

    fun nextPokeId():String{
        return currentPokemon.value?.id?.plus(1).toString()
    }
}
