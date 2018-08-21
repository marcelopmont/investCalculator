package com.mpm.investcalculator.models

class InvestmentRequestModel(
        val investedAmount: Double,
        val index: String = "CDI",
        val rate: Int,
        val isTaxFree: Boolean = false,
        val maturityDate: String)