package com.mpm.investcalculator.utils

class StringUtils {

    fun convertToDateFormat(text: String): String {
        var finalDate = getNumbersFromString(text, false).toInt().toString()
        if (finalDate.length > 4) {
            finalDate = finalDate.substring(0, 2) + "/" + finalDate.substring(2, 4) + "/" + finalDate.substring(4, finalDate.length)
        } else if (finalDate.length > 2) {
            finalDate = finalDate.substring(0, 2) + "/" + finalDate.substring(2, finalDate.length)
        }
        return finalDate
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