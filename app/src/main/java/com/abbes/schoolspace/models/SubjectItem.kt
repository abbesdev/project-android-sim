package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class SubjectItem(
    @SerializedName("_id")
    val id: String,
    @SerializedName("nameSubject")
    val nameSubject: String,
    @SerializedName("imageSubject")
    val imageSubject: String,
    @SerializedName("__v")
    val v: Int
)