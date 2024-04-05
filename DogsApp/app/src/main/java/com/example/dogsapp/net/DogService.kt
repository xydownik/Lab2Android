package com.example.dogsapp.net


import retrofit2.Call
import retrofit2.http.GET

interface DogService {

    @GET("dogs?energy=5")
    fun fetchDogList(): Call<List<Dog>>
}