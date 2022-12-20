package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class UserSignInResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("childrens")
    val childrens: List<String>

)