package com.example.android.covid19tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private lateinit var stateText: TextView
    private lateinit var confirmedText: TextView
    private lateinit var deathText: TextView
    private lateinit var dischargedText: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initializeViews()

        val startIntent = getIntent()
        setTextViews(startIntent)

        backButton.setOnClickListener { view ->
            goBacktoMain(view)
        }
    }

    private fun setTextViews(startIntent: Intent) {
        val state = startIntent.getStringExtra("state")
        val totalConfirmed = startIntent.getIntExtra("totalConfirmed",0)
        val deaths = startIntent.getIntExtra("deaths",0)
        val discharged = startIntent.getIntExtra("discharged",0)

        stateText.setText(state)
        confirmedText.setText(totalConfirmed.toString())
        deathText.setText(deaths.toString())
        dischargedText.setText(discharged.toString())
    }

    private fun goBacktoMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initializeViews() {
        stateText = findViewById(R.id.tvState)
        confirmedText = findViewById(R.id.tvTotConfirmed)
        deathText = findViewById(R.id.tvDeaths)
        dischargedText = findViewById(R.id.tvDischarged)
        backButton = findViewById(R.id.btnBack)
    }
}