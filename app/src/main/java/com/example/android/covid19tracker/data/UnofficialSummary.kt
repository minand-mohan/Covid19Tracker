package com.example.android.covid19tracker.data

data class UnofficialSummary(
    val active: Int,
    val deaths: Int,
    val recovered: Int,
    val source: String,
    val total: Int
)