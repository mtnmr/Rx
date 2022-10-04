package com.example.rxjavasample

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main(){
    println("main start")

    Observable.create{ emitter ->
        println(Thread.currentThread().name)

        val tokyoWeather = RestUtil.getWeather(RestUtil.Place.TOKYO)
        emitter.onNext(tokyoWeather)

        val yokohamaWeather = RestUtil.getWeather(RestUtil.Place.YOKOHAMA)
        emitter.onNext(yokohamaWeather)

        val nagoyaWeather = RestUtil.getWeather(RestUtil.Place.NAGOYA)
        emitter.onNext(nagoyaWeather)

        emitter.onComplete()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.newThread())
        .subscribe(
        { weather ->
            println("Next!")
            println(weather)
            println(Thread.currentThread().name)
        },
        { throwable ->
            println("Error!")
            throwable.printStackTrace()
        },
        { println("Complete!") }
    )

    Thread.sleep(5000)

    println("main end")
}