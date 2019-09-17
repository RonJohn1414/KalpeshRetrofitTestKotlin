package com.example.kalpeshretrofittestkotlin.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.kalpeshretrofittestkotlin.model.DataList as DataList

class DataService {
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private val api: DataApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DataApi::class.java)
    }

    fun getData(): Single<List<DataList>>{
        return api.getData()
    }
}