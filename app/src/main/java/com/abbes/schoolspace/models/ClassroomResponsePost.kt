package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class ClassroomResponsePost(
    @SerializedName("classes")
    val classes: List<String>,
    @SerializedName("classroomTitle")
    val classroomTitle: String,
    @SerializedName("homework")
    val homework: List<Any>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("students")
    val students: List<String>,
    @SerializedName("subject")
    val subject: List<String>,
    @SerializedName("teacher")
    val teacher: List<String>,
    @SerializedName("__v")
    val v: Int
)