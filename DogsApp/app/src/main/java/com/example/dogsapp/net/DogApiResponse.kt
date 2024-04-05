package com.example.dogsapp.net

data class DogApiResponse (
    val page: Int,
    val results: List<Dog> = listOf()
)