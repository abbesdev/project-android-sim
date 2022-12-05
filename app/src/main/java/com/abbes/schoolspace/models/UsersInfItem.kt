package com.abbes.schoolspace.models

data class UsersInfItem(
    val __v: Int,
    val _id: String,
    val confirmed: Boolean,
    val email: String,
    val fullname: String,
    val password: String,
    val roles: List<String>,
    var visibility : Boolean = false

)