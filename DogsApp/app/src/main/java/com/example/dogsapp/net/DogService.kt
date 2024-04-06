package com.example.dogsapp.net


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DogService {

    @GET("dogs")
    fun fetchDogList(@Query("name") name: String): Call<List<Dog>>
}