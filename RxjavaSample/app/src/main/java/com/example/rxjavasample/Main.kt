package com.example.rxjavasample

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

fun main() {
    println("main start")

    val tokyoWeather = RestUtil.getWeather(RestUtil.Place.TOKYO)
    val yokohamaWeather = RestUtil.getWeather(RestUtil.Place.YOKOHAMA)
    val nagoyaWeather = RestUtil.getWeather(RestUtil.Place.NAGOYA)

    println(tokyoWeather)
    println(yokohamaWeather)
    println(nagoyaWeather)

    println("main end")
}
