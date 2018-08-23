package com.mpm.investcalculator.modules

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.mpm.investcalculator.R
import com.mpm.investcalculator.models.InvestmentResponseModel

import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    companion object {
        const val RESPONSE_MODEL = "response model"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setupButtons()
        populateData()
    }

    private fun setupButtons() {
        resultSimulateAgainButton.setOnClickListener {
            finish()
        }
    }

    private fun populateData() {
        val investmentResponseModel = intent.getParcelableExtra<InvestmentResponseModel>(RESPONSE_MODEL)

        resultValue.text = investmentResponseModel.getFormattedGrossAmount()
        resultEarningsValue.text = investmentResponseModel.getFormattedGrossAmountProfit()
        resultValueApplied.text = investmentResponseModel.getFormattedInvestedAmount()
        resultGrossValue.text = investmentResponseModel.getFormattedGrossAmount()
        resultIncomeValue.text = investmentResponseModel.getFormattedGrossAmountProfit()
        resultIRValue.text = investmentResponseModel.getFormattedTaxesRate()
        resultNetValue.text = investmentResponseModel.getFormattedNetAmount()
        resultRedemptionDate.text = investmentResponseModel.getFormattedMaturityDate()
        resultConsecutiveDays.text = investmentResponseModel.investmentParameter.maturityTotalDays.toString()
        resultMonthlyIncome.text = investmentResponseModel.getFormattedMonthlyGrossRateProfit()
        resultPercentualCDI.text = investmentResponseModel.getFormattedRate()
        resultAnnualProfit.text = investmentResponseModel.getFormattedAnnualGrossRateProfit()
        resultPeriodProfit.text = investmentResponseModel.getFormattedRateProfit()
    }

}
