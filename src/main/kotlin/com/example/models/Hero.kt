package com.example.models

import kotlinx.serialization.Serializable


@Serializable
data class Event(
    val id:Int,
    val name :String,
    val image:String,
    val about:String,
    val rating:Double,
    val month:String,
    val day:String
)
