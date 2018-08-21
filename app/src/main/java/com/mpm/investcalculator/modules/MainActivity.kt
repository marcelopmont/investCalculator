package com.mpm.investcalculator.modules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mpm.investcalculator.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButton()
    }

    private fun setupButton() {
        simulateButton.setOnClickListener {
            onSimulateButtonClicked()
        }
    }

    private fun onSimulateButtonClicked() {

    }
}
