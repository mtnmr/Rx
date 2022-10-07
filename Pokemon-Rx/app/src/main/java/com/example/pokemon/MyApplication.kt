package com.example.pokemon

import android.app.Application
import com.example.pokemon.api.PokeRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {

//    val repository : PokeRepository by lazy {
//        PokeRepository.getRepository()
//    }
}