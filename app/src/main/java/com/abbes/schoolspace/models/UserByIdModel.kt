package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class UserByIdModel(
    @SerializedName("childrens")
    val childrens: List<Any>,
    @SerializedName("classes")
    val classes: List<Any>,
    @SerializedName("confirmed")
    val confirmed: Boolean,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("__v")
    val v: Int
)