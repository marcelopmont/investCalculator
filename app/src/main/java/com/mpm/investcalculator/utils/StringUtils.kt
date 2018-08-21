package com.mpm.investcalculator.utils

class StringUtils {

    fun convertToMoneyFormat(text: String) {
        val formatted = String.format("R$ %.2f", getDoubleFromMoneyString(text.toString()))
    }

    fun getDoubleFromMoneyString(text: String): Double {
        val removeRegex = Regex("[^x0-9]")
        val cleanString = removeRegex.replace(text, "")

        return cleanString.toDouble() / 100
    }

}