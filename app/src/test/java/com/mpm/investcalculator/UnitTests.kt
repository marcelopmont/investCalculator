package com.mpm.investcalculator

import com.mpm.investcalculator.utils.StringUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * This class is testing every method from StringUtils class
 */
class UnitTests {

    val stringUtils = StringUtils()

    @Test
    fun brazilDateFormat() {
        val day = 14
        val month = 10
        val year = 1991

        val finalDate = stringUtils.convertToBrazilianDateFormat(day, month, year)

        assertEquals("14/10/1991", finalDate)
    }

    @Test
    fun brazilDateFormatFromDateString() {
        val dateString = "1991-10-14T00:00:00"

        val finalDate = stringUtils.convertToBrazilianDateFormat(dateString)

        assertEquals("14/10/1991", finalDate)
    }

    @Test
    fun requestDateFormat() {
        val day = 14
        val month = 10
        val year = 1991

        val finalDate = stringUtils.convertToRequestDateFormat(day, month, year)

        assertEquals("1991-10-14", finalDate)
    }

    @Test
    fun moneyFormatFromText() {
        val value = "1500"

        val finalValue = stringUtils.convertToMoneyFormat(value)

        assertEquals("R$ 15.00", finalValue)
    }

    @Test
    fun moneyFormatFromNumber() {
        val value = 15.0

        val finalValue = stringUtils.convertToMoneyFormat(value)

        assertEquals("R$ 15.00", finalValue)
    }

    @Test
    fun percentageFromText() {
        val value = "5"

        val finalValue = stringUtils.convertToPercentage(value)

        assertEquals("5%", finalValue)
    }

    @Test
    fun percentageFromNumber() {
        val value = 5.5

        val finalValue = stringUtils.convertToPercentage(value)

        assertEquals("5.5%", finalValue)
    }

    @Test
    fun numberFromString() {
        val value = "5000"

        var finalValue = stringUtils.getNumbersFromString(value, false)

        check(5000.0 == finalValue)

        finalValue = stringUtils.getNumbersFromString(value, true)

        check(50.0 == finalValue)
    }

}
