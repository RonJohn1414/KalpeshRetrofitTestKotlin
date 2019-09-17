package com.example.kalpeshretrofittestkotlin.model

import com.google.gson.annotations.SerializedName

 data class DataList (
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("id")
    val entryId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?
)
