package com.example.android.covid19tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.android.covid19tracker.data.AppData
import com.example.android.covid19tracker.data.CovidData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException

const val BASE_URL = "https://api.rootnet.in/covid19-in/stats/"
private const val TAG = "Covid19 app"

class MainActivity : AppCompatActivity() {

    private lateinit var getDataButton: Button
    private lateinit var resultText: TextView
    private lateinit var stateSpinner: Spinner

    private lateinit var retrofit: Retrofit
    private lateinit var covid19DataApi: Covid19DataApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initilaizeViews()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        covid19DataApi = retrofit.create(Covid19DataApi::class.java)

        getDataButton.setOnClickListener{ view ->
            val stateCodeSelected = stateSpinner.selectedItem.toString()
            getCovidDataAndDisplay(view, stateCodeSelected)
        }
    }

    private fun getCovidDataAndDisplay(view: View?, stateCodeSelected: String) {
        val call = covid19DataApi.getLatestCovidData()

        call.enqueue(object: Callback<CovidData>{
            override fun onResponse(call: Call<CovidData>, response: Response<CovidData>) {

                if(!response.isSuccessful){
                    resultText.setText("Code: ${response.code()}")
                    return
                }

                displayCovidDataFromResponse(response)


            }

            private fun displayCovidDataFromResponse(response: Response<CovidData>) {
                val stateSelected = AppData.stateMap.get(stateCodeSelected)
                Log.d(TAG, "displayCovidDataFromResponse: $stateCodeSelected $stateSelected")
                val body: CovidData? =  response.body()
                if(stateSelected == null && body == null){
                    Log.e(TAG, "displayCovidDataFromResponse: stateSelected/ response body is null" )
                    return
                }
                for (regional in body?.data!!.regional) {
                    if(regional.loc == stateSelected){

                        val intent = Intent(this@MainActivity,ResultActivity::class.java)
                        intent.putExtra("state", regional.loc)
                        intent.putExtra("totalConfirmed", regional.totalConfirmed)
                        intent.putExtra("deaths", regional.deaths)
                        intent.putExtra("discharged", regional.discharged)

                        startActivity(intent)
                    }else{
                        continue
                    }
                }
            }

            override fun onFailure(call: Call<CovidData>, t: Throwable) {
                resultText.setText(t.message)
            }

        })
    }

    private fun initilaizeViews() {
        getDataButton = findViewById(R.id.btnGetData)
        resultText = findViewById(R.id.tvResult)
        stateSpinner = findViewById(R.id.spnStates)
    }
}