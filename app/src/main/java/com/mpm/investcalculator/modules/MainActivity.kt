package com.mpm.investcalculator.modules

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.mpm.investcalculator.R
import com.mpm.investcalculator.models.InvestmentRequestModel
import com.mpm.investcalculator.models.InvestmentResponseModel
import com.mpm.investcalculator.services.CalculatorService
import com.mpm.investcalculator.utils.StringUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.OnFocusChangeListener
import android.widget.DatePicker
import kotlinx.android.synthetic.main.loading_view.*
import java.util.*


class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        const val BUTTON_OPAQUE = 1f
        const val BUTTON_TRANSLUCENT = 0.3f
    }

    val stringUtils = StringUtils()

    var selectedDay = 0
    var selectedMonth = 0
    var selectedYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupInitialDate()
        setupTextListeners()
        setupButton()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        selectedDay = day
        selectedMonth = month + 1
        selectedYear = year

        homeDeadlineInput.setText(stringUtils.convertToBrazilianDateFormat(selectedDay, selectedMonth, selectedYear))

        enableSimulateButton(isFieldsCompleted())
    }

    private fun setupInitialDate() {
        val calendar = Calendar.getInstance()

        selectedDay = calendar.get(Calendar.DAY_OF_MONTH)
        selectedMonth = calendar.get(Calendar.MONTH) + 1
        selectedYear = calendar.get(Calendar.YEAR)
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

                    enableSimulateButton(isFieldsCompleted())
                }

            }
        })

        homeDeadlineInput.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val datePickerDialog = DatePickerDialog(this, this@MainActivity, selectedYear, selectedMonth - 1, selectedDay)
                datePickerDialog.show()
            }
            homePercentageInput.requestFocus()

        }

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

                    enableSimulateButton(isFieldsCompleted())
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

        loadingView.visibility = View.VISIBLE

        calculatorService.subscribeForCDI(getInvestmentRequestModel(),
                { response ->
                    onCalculatorResponseSuccess(response)
                    loadingView.visibility = View.GONE
                },
                {
                    onCalculatorResponseError()
                    loadingView.visibility = View.GONE
                })
    }

    private fun getInvestmentRequestModel(): InvestmentRequestModel {
        val investedAmount = stringUtils.getNumbersFromString(homeApplyLabelInput.text.toString(), true)
        val rate = stringUtils.getNumbersFromString(homePercentageInput.text.toString(), false).toInt()
        val maturityDate = stringUtils.convertToRequestDateFormat(selectedDay, selectedMonth, selectedYear)

        return InvestmentRequestModel(investedAmount, "CDI", rate, false, maturityDate)
    }

    private fun clearFields() {
        setupInitialDate()
        homeApplyLabelInput.setText("")
        homePercentageInput.setText("")
        homeDeadlineInput.setText("")
        enableSimulateButton(false)
    }

    private fun isFieldsCompleted(): Boolean {
        return homeApplyLabelInput.text.isNotEmpty() && homePercentageInput.text.isNotEmpty() && homeDeadlineInput.text.isNotEmpty()
    }

    private fun enableSimulateButton(isEnable: Boolean) {
        simulateButton.isEnabled = isEnable
        if (isEnable) {
            simulateButton.alpha = BUTTON_OPAQUE
        } else {
            simulateButton.alpha = BUTTON_TRANSLUCENT
        }
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
