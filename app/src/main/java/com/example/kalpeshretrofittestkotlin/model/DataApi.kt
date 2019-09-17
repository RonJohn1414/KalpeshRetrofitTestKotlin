package com.example.kalpeshretrofittestkotlin.model


import io.reactivex.Single
import retrofit2.http.GET

interface DataApi {
    @GET("posts")
    fun getData(): Single<List<DataList>>
}