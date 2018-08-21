package com.mpm.investcalculator.models

class InvestmentRequestResponseModel(
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
        val annualNetRateProfit: Double
)