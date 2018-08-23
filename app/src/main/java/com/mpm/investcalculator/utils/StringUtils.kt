package com.mpm.investcalculator.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class StringUtils {

    fun convertToBrazilianDateFormat(day: Int, month: Int, year: Int): String {
        return day.toString() + "/" + month + "/" + year
    }

    fun convertToBrazilianDateFormat(date: String): String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        var dateParsed = Date()

        try {
            dateParsed = df.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar = Calendar.getInstance()
        calendar.time = dateParsed

        return convertToBrazilianDateFormat(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR))
    }

    fun convertToRequestDateFormat(day: Int, month: Int, year: Int): String {
        return year.toString() + "-" + month + "-" + day
    }

    fun convertToMoneyFormat(text: String): String {
        return String.format("R$ %.2f", getNumbersFromString(text, true))
    }

    fun convertToMoneyFormat(number: Double): String {
        return String.format("R$ %.2f", number)
    }

    fun convertToPercentage(text: String): String {
        return String.format("%d%%", getNumbersFromString(text, false).toInt())
    }

    fun convertToPercentage(number: Double): String {
        return String.format("%.1f%%", number)
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