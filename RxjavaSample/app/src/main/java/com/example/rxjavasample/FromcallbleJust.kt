package com.example.rxjavasample

import io.reactivex.rxjava3.core.Single
import kotlin.random.Random

fun main() {
    println("From Just")
    val justSingle = Single.just(getRandomMessage())
    println("start subscribing")
    justSingle.subscribe{ it -> println(it) }

    println("From Callable")
    val callableSingle = Single.fromCallable { getRandomMessage() }
    //subscribeされて初めて値を生成する
    println("start subscribing")
    callableSingle.subscribe{ it -> println(it) }
}

fun getRandomMessage(): Int {
    println("-Generating-")
    return Random.nextInt()
}