package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class SignUpWithRole(
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("roles")
    val roles: List<String>
)