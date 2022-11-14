package com.abbes.schoolspace.models

import androidx.annotation.DrawableRes

const val PICTURE = "PICTURE"
const val NAME = "NAME"


data class Course(


        @DrawableRes
        val coursePic: Int,

        val courseName: String,

    )
