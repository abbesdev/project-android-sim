package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class ClassroomResponseByClass(
    @SerializedName("classes")
    val classes: String
)