package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class SubjectByName(
    @SerializedName("subjects")
    val subjects: String
)