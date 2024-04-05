package com.example.dogsapp.net
import com.google.gson.annotations.SerializedName
import java.util.UUID
data class Dog(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val energy: Int,
    val trainability: Int,
    val protectiveness: Int,
    @SerializedName("min_life_expectancy")val minLifeExpectancy: Int,
    @SerializedName("image_link") val imageLink: String
)