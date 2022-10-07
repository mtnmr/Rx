package com.example.pokemon.model

import com.squareup.moshi.Json

data class Pokemon (
    val id : Int,
    val name : String,
    val height : Int,
    val weight : Int,
    val types : List<PokeType>,
    val sprites : PokeSprite
){

    fun getHeight(): String =
        "%.1f".format(height / 10.0)

    fun getWeight(): String =
        "%.1f".format(weight / 10.0)

    fun getPokeType() : String{
        val typeList = mutableListOf<String>()
        for (type in types){
            typeList.add(type.type.name)
        }
        return typeList.joinToString(separator = " , ")
    }

    fun getImage(): String{
        return sprites.other.officialArtwork.frontDefault
    }
}

data class  PokeSprite(
    @Json(name = "front_default") val frontDefault : String,
    @Json(name = "back_default") val backDefault : String,
    @Json(name = "front_female") val frontFemale: String?,
    @Json(name = "back_female") val backFemale : String?,
    val other : SpritesOther
)

data class SpritesOther(
    @Json(name = "official-artwork") val officialArtwork : PokeImage
)

data class PokeImage(
    @Json(name = "front_default") val frontDefault: String
)

data class PokeType(
    val slot :Int,
    val type : NameApiResource
)

data class NameApiResource(
    val name : String,
    val url : String
)