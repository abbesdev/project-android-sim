package com.abbes.schoolspace.models

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("fullname")  val fullname: String? = null,
    @SerializedName("email")  val email: String? = null,
    @SerializedName("password")  val password: String? = null


)