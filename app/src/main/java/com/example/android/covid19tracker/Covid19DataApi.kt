package com.example.android.covid19tracker

import com.example.android.covid19tracker.data.CovidData
import retrofit2.Call
import retrofit2.http.GET

interface Covid19DataApi {

    @GET("latest")
    fun getLatestCovidData(): Call<CovidData>
}