package com.example.rxjavasample

import java.util.*


object RestUtil {

    fun getWeather(place: Place?): Weather {

        val weather: Weather = when (place) {
            Place.TOKYO -> Weather.SUNNY
            Place.YOKOHAMA -> Weather.RAINY
            Place.NAGOYA -> Weather.WINDY
            else -> Weather.SUNNY
        }
        try {
            Thread.sleep((Random().nextInt(500) + 500).toLong())
        } catch (e: InterruptedException) {
        }
        return weather
    }

    enum class Weather {
        SUNNY, RAINY, WINDY
    }

    enum class Place {
        TOKYO, YOKOHAMA, NAGOYA
    }
}