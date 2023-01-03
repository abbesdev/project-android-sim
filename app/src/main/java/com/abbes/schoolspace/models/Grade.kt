package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class Grade(
    @SerializedName("gradeName")
    val gradeName: String,
    @SerializedName("gradeValue")
    val gradeValue: String,
    @SerializedName("_id")
    val id: String
)