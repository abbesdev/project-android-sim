package com.abbes.schoolspace.models

import android.media.TimedMetaData
import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class MatiereInfo (

    /*
    val classroom : String,
    val date: LocalDate,
    val timestart: LocalTime,
    val timeend: LocalTime,
    
     */

    
    val __v: Int,
    @SerializedName("_id") val classroom: String,
    val classes: List<String>,
    @SerializedName("enddate") val timeend: java.util.Date,
    @SerializedName("startdate") val timestart: java.util.Date,
    val title: String,
    var test : String =""
)