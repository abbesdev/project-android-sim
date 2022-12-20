package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class HomeworkItem(
    @SerializedName("classroom")
    val classroom: List<String>,
    @SerializedName("homeworkDeadline")
    val homeworkDeadline: String,
    @SerializedName("homeworkDescription")
    val homeworkDescription: String,
    @SerializedName("homeworkFiles")
    val homeworkFiles: List<String>,
    @SerializedName("homeworkStatus")
    val homeworkStatus: Boolean,
    @SerializedName("homeworkTitle")
    val homeworkTitle: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("subject")
    val subject: List<String>,
    @SerializedName("__v")
    val v: Int
)