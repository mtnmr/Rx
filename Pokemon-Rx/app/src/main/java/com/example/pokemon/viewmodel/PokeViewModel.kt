package com.example.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.api.PokeRepository
import com.example.pokemon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(private val repository: PokeRepository):ViewModel() {

    val pokemon:PublishSubject<Pokemon> = PublishSubject.create()

    fun getPoke(id:String){
        repository.getPoke(id)
            .subscribe{ poke ->
                pokemon.onNext(poke)
            }
    }

//    fun getPoke(id:String):Single<Pokemon>{
//        return repository.getPoke(id)
//    }
}
