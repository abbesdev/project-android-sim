package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class GradeVerifyPostRequest(
    @SerializedName("students")
    val students: List<String>,
    @SerializedName("subject")
    val subject: List<String>
)