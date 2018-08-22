package com.mpm.investcalculator.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class InvestmentParameterModel(
        val investedAmount: Double,
        val yearlyInterestRate: Double,
        val maturityTotalDays: Int,
        val maturityBusinessDays: Int,
        val maturityDate: String,
        val rate: Double,
        val isTaxFree: Boolean): Parcelable