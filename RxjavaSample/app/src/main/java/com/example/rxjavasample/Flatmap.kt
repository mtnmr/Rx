package com.example.rxjavasample

import io.reactivex.rxjava3.core.Observable

fun main(){
    println("main start")

    Observable.create{ emitter ->
        emitter.onNext(RestUtil.Place.TOKYO)
        emitter.onNext(RestUtil.Place.YOKOHAMA)
        emitter.onNext(RestUtil.Place.NAGOYA)
        emitter.onComplete()
    }.flatMap { place ->
        Observable.create { emitter ->
            val tokyoWeather = RestUtil.getWeather(place)
            emitter.onNext(tokyoWeather)
            emitter.onComplete()
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