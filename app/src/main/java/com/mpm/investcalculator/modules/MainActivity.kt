package com.mpm.investcalculator.modules

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import com.mpm.investcalculator.R
import com.mpm.investcalculator.models.InvestmentRequestModel
import com.mpm.investcalculator.models.InvestmentResponseModel
import com.mpm.investcalculator.services.CalculatorService
import com.mpm.investcalculator.utils.StringUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val stringUtils = StringUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTextListeners()
        setupButton()
    }

    private fun setupTextListeners() {
        homeApplyLabelInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    homeApplyLabelInput.removeTextChangedListener(this)
                    val formattedValue = stringUtils.convertToMoneyFormat(p0.toString())
                    homeApplyLabelInput.setText(formattedValue)
                    homeApplyLabelInput.setSelection(formattedValue.length)
                    homeApplyLabelInput.addTextChangedListener(this)
                }

            }
        })

        homeDeadlineInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    homeDeadlineInput.removeTextChangedListener(this)
                    val formattedValue = stringUtils.convertToDateFormat(p0.toString())
                    homeDeadlineInput.setText(formattedValue)
                    homeDeadlineInput.setSelection(formattedValue.length)
                    homeDeadlineInput.addTextChangedListener(this)
                }

            }
        })

        homePercentageInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    homePercentageInput.removeTextChangedListener(this)
                    val formattedValue = stringUtils.convertToPercentage(p0.toString())
                    homePercentageInput.setText(formattedValue)
                    homePercentageInput.setSelection(formattedValue.length - 1)
                    homePercentageInput.addTextChangedListener(this)
                }

            }
        })


    }

    private fun setupButton() {
        simulateButton.setOnClickListener {
            onSimulateButtonClicked()
        }
    }

    private fun onSimulateButtonClicked() {
        val calculatorService = CalculatorService()

        calculatorService.subscribeForCDI(getInvestmentRequestModel(),
                { response ->
                    onCalculatorResponseSuccess(response)
                },
                {
                    onCalculatorResponseError()
                })
    }

    private fun getInvestmentRequestModel(): InvestmentRequestModel {
        val investedAmount = homeApplyLabelInput.text.toString().toDouble()
        val rate = homePercentageInput.text.toString().toInt()
        val maturityDate = homeDeadlineInput.text.toString()

        return InvestmentRequestModel(investedAmount, "CDI", rate, false, maturityDate)
    }

    private fun clearFields() {
        homeApplyLabelInput.setText("")
        homePercentageInput.setText("")
        homeDeadlineInput.setText("")
    }

    private fun onCalculatorResponseSuccess(investmentResponseModel: InvestmentResponseModel) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(ResultActivity.RESPONSE_MODEL, investmentResponseModel)
        startActivity(intent)
        clearFields()
    }

    private fun onCalculatorResponseError() {
        Snackbar.make(mainContainer, getString(R.string.request_fail), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}
