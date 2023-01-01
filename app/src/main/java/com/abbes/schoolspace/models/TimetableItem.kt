package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class TimetableItem(
    @SerializedName("classes")
    val classes: List<String>,
    @SerializedName("enddate")
    val enddate: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("startdate")
    val startdate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("__v")
    val v: Int
)