package com.abbes.schoolspace.models

import com.google.gson.annotations.SerializedName

data class UserInfo(

    val _id: String,
    @SerializedName("fullname") val fullName: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("confirmed") val confirmed: Boolean?,
    var visibility : Boolean = false

    )
