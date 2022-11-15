package com.abbes.schoolspace.models

import com.google.gson.annotations.SerializedName

data class UserInfo(

    @SerializedName("fullname") val fullName: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?

    )
