package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class StudentWithGradeByIdModelItem(
    @SerializedName("gradeName")
    val gradeName: String,
    @SerializedName("gradeValue")
    val gradeValue: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("students")
    val students: List<String>,
    @SerializedName("subject")
    val subject: List<String>,
    @SerializedName("__v")
    val v: Int
)