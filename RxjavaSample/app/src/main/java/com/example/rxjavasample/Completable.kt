package com.example.rxjavasample

import io.reactivex.rxjava3.core.Completable

fun main() {
    println("main start")

    Completable.create { emitter ->
        val tokyoWeather = RestUtil.getWeather(RestUtil.Place.TOKYO)
        val yokohamaWeather = RestUtil.getWeather(RestUtil.Place.YOKOHAMA)
        val nagoyaWeather = RestUtil.getWeather(RestUtil.Place.NAGOYA)

        println(tokyoWeather)
        println(yokohamaWeather)
        println(nagoyaWeather)
        emitter.onComplete()
    }.subscribe(
        { println("Complete!") },
        { throwable ->
            println("Error!")
            throwable.printStackTrace()
        }
    )

    println("main end")
}