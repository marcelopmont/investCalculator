package com.mpm.investcalculator.models

import android.os.Parcelable
import com.mpm.investcalculator.utils.StringUtils
import kotlinx.android.parcel.Parcelize

@Parcelize
class InvestmentResponseModel(
        val investmentParameter: InvestmentParameterModel,
        val grossAmount: Double,
        val taxesAmount: Double,
        val netAmount: Double,
        val grossAmountProfit: Double,
        val netAmountProfit: Double,
        val annualGrossRateProfit: Double,
        val monthlyGrossRateProfit: Double,
        val dailyGrossRateProfit: Double,
        val taxesRate: Double,
        val rateProfit: Double,
        val annualNetRateProfit: Double): Parcelable {

    val stringUtils = StringUtils()

    fun getFormattedGrossAmount(): String {
        return stringUtils.convertToMoneyFormat(grossAmount)
    }

    fun getFormattedGrossAmountProfit(): String {
        return stringUtils.convertToMoneyFormat(grossAmountProfit)
    }

    fun getFormattedInvestedAmount(): String {
        return stringUtils.convertToMoneyFormat(investmentParameter.investedAmount)
    }

    fun getFormattedTaxesRate(): String {
        return stringUtils.convertToMoneyFormat(taxesAmount) + "(" + stringUtils.convertToPercentage(taxesRate) + ")"
    }

    fun getFormattedNetAmount(): String {
        return stringUtils.convertToMoneyFormat(netAmount)
    }

    fun getFormattedMonthlyGrossRateProfit(): String {
        return stringUtils.convertToPercentage(monthlyGrossRateProfit)
    }

    fun getFormattedRate(): String {
        return stringUtils.convertToPercentage(investmentParameter.rate)
    }

    fun getFormattedAnnualGrossRateProfit(): String {
        return stringUtils.convertToPercentage(annualGrossRateProfit)
    }

    fun getFormattedRateProfit(): String {
        return stringUtils.convertToPercentage(rateProfit)
    }

    fun getFormattedMaturityDate(): String {
        return stringUtils.convertToBrazilianDateFormat(investmentParameter.maturityDate)
    }


}