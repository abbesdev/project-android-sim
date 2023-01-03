package com.abbes.schoolspace.models


import com.google.gson.annotations.SerializedName

data class RoleBodyRequestSignup(
    @SerializedName("roles")
    val roles: List<String>
)