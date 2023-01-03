package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class ClassroomPost(
    @SerializedName("classes")
    val classes: List<String>,
    @SerializedName("classroomTitle")
    val classroomTitle: String,
    @SerializedName("students")
    val students: List<String>,
    @SerializedName("subject")
    val subject: List<String>,
    @SerializedName("teacher")
    val teacher: List<String>
)