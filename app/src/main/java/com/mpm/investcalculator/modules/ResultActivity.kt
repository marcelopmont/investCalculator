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

        populateData()
    }

    fun populateData() {
        val investmentResponseModel = intent.getParcelableExtra<InvestmentResponseModel>(RESPONSE_MODEL)

        resultValue.text = investmentResponseModel.grossAmount.toString()
        resultEarningsValue.text = investmentResponseModel.grossAmountProfit.toString()
        resultValueApplied.text = investmentResponseModel.investmentParameter.investedAmount.toString()
        resultGrossValue.text = investmentResponseModel.grossAmount.toString()
        resultIncomeValue.text = investmentResponseModel.grossAmountProfit.toString()
        resultIRValue.text = investmentResponseModel.taxesRate.toString()
        resultNetValue.text = investmentResponseModel.netAmount.toString()
        resultRedemptionDate.text = investmentResponseModel.investmentParameter.maturityDate
        resultConsecutiveDays.text = investmentResponseModel.investmentParameter.maturityTotalDays.toString()
        resultMonthlyIncome.text = investmentResponseModel.monthlyGrossRateProfit.toString()
        resultPercentualCDI.text = investmentResponseModel.investmentParameter.rate.toString()
        resultAnnualProfit.text = investmentResponseModel.annualGrossRateProfit.toString()
        resultPeriodProfit.text = investmentResponseModel.rateProfit.toString()
    }

}
