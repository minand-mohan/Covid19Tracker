package com.example.android.covid19tracker.data

data class Summary(
    val confirmedButLocationUnidentified: Int,
    val confirmedCasesForeign: Int,
    val confirmedCasesIndian: Int,
    val deaths: Int,
    val discharged: Int,
    val total: Int
)