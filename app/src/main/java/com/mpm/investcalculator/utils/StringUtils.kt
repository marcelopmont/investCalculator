package com.mpm.investcalculator.utils

class StringUtils {

    fun convertToBrazilianDateFormat(day: Int, month: Int, year: Int): String {
        return day.toString() + "/" + month + "/" + year
    }

    fun convertToRequestDateFormat(day: Int, month: Int, year: Int): String {
        return year.toString() + "-" + month + "-" + day
    }

    fun convertToMoneyFormat(text: String): String {
        return String.format("R$ %.2f", getNumbersFromString(text, true))
    }

    fun convertToPercentage(text: String): String {
        return String.format("%d%%", getNumbersFromString(text, false).toInt())
    }



    fun getNumbersFromString(text: String, isMoney: Boolean): Double {
        val removeRegex = Regex("[^x0-9]")
        val cleanString = removeRegex.replace(text, "")

        if (cleanString.isEmpty()) {
            return 0.0
        }

        if (isMoney) {
            return cleanString.toDouble() / 100
        }

        return cleanString.toDouble()

    }

}