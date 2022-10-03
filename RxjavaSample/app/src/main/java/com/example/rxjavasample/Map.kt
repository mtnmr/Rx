package com.example.rxjavasample

import io.reactivex.rxjava3.core.Observable

fun main(){
    println("main start")

    Observable.create{ emitter ->
        val tokyoWeather = RestUtil.getWeather(RestUtil.Place.TOKYO)
        emitter.onNext(tokyoWeather)

        val yokohamaWeather = RestUtil.getWeather(RestUtil.Place.YOKOHAMA)
        emitter.onNext(yokohamaWeather)

        val nagoyaWeather = RestUtil.getWeather(RestUtil.Place.NAGOYA)
        emitter.onNext(nagoyaWeather)

        emitter.onComplete()
    }.map {weather ->
        when(weather){
            RestUtil.Weather.SUNNY -> "happy"
            RestUtil.Weather.RAINY -> "sad"
            RestUtil.Weather.WINDY -> "normal"
        }
    }.subscribe(
        { weather ->
            println("Next!")
            println(weather)
        },
        { throwable ->
            println("Error!")
            throwable.printStackTrace()
        },
        { println("Complete!") }
    )

    println("main end")
}