package com.example.android.covid19tracker.data

data class Regional(
    val confirmedCasesForeign: Int,
    val confirmedCasesIndian: Int,
    val deaths: Int,
    val discharged: Int,
    val loc: String,
    val totalConfirmed: Int
)