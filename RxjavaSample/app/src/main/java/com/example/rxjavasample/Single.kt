package com.example.rxjavasample

import io.reactivex.rxjava3.core.Single

fun main(){
    println("main start")

    Single.create<List<RestUtil.Weather>> { emitter ->
        val tokyoWeather = RestUtil.getWeather(RestUtil.Place.TOKYO)
        val yokohamaWeather = RestUtil.getWeather(RestUtil.Place.YOKOHAMA)
        val nagoyaWeather = RestUtil.getWeather(RestUtil.Place.NAGOYA)
        emitter.onSuccess(listOf(tokyoWeather, yokohamaWeather, nagoyaWeather))
    }.subscribe(
        { weathers ->
            println("Complete!")
            for (weather in weathers) {
                println(weather)
            }
        },
        { throwable ->
            println("Error!")
            throwable.printStackTrace()
        }
    )

    println("main end")
}