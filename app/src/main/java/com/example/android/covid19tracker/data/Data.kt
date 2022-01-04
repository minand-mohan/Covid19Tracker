package com.example.android.covid19tracker.data

data class Data(
    val regional: List<Regional>,
    val summary: Summary,
    val unofficial_summary: List<UnofficialSummary>
)