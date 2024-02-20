package com.example.pathtracker

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod

class MainActivity : AppCompatActivity() {

    private val stopsNormalList = listOf("Govindpuri", "Kalkaji Mandir", "Nehru Place", "Greater Kailash", "Moolchand", "Lajpat Nagar", "Jangpura", "JLN Stadium", "Khan Market", "Central Secretariat", "Patel Chowk", "Rajiv Chowk", "RK Ashram Marg", "Jhandewalan", "Karol Bagh")

    private var currentStopIndex = 0
    private var totalDistanceCovered = 0.0
    private val totalDistance = 150.0
    private var distanceUnitKm = true

    private lateinit var stopTextView: TextView
    private lateinit var distanceTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var locationText: TextView
    private lateinit var realtimeLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopTextView = findViewById(R.id.stopTextView)
        distanceTextView = findViewById(R.id.distanceTextView)
        progressBar = findViewById(R.id.progressBar)
        locationText = findViewById(R.id.location_text)
        realtimeLocation = findViewById(R.id.realtime_location)

        val switchUnitButton: Button = findViewById(R.id.switchUnitButton)
        val nextStopButton: Button = findViewById(R.id.nextStopButton)

        switchUnitButton.setOnClickListener {
            distanceUnitKm = !distanceUnitKm
            updateDistance()
        }

        nextStopButton.setOnClickListener {
            if (currentStopIndex < stopsNormalList.size - 1) {
                currentStopIndex++
                updateProgress()
            }
        }
        locationText.text = "Stations List\n"
        for(i in stopsNormalList){
            realtimeLocation.append("$i\n")
        }
        realtimeLocation.movementMethod = ScrollingMovementMethod.getInstance()

        // Initialize UI with first stop
        updateProgress()
    }


    private fun updateProgress() {
        stopTextView.text = stopsNormalList[currentStopIndex]
        val distanceToNextStop = 10.0
        totalDistanceCovered += distanceToNextStop
        updateDistance()
        progressBar.progress = ((totalDistanceCovered / totalDistance) * 100).toInt()
    }

    private fun updateDistance() {
        val distanceToNextStop = 10.0
        val distanceUnit = if (distanceUnitKm) "km" else "miles"
        val distance = if (distanceUnitKm) distanceToNextStop else convertKmToMiles(distanceToNextStop)
        distanceTextView.text = String.format("%.2f %s", distance, distanceUnit)
    }

    private fun convertKmToMiles(km: Double): Double {
        return km * 0.621371
    }
}