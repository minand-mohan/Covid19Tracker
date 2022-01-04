package com.example.android.covid19tracker.data

data class CovidData(
    val `data`: Data,
    val lastOriginUpdate: String,
    val lastRefreshed: String,
    val success: Boolean
)