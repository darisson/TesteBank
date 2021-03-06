package com.example.testebankdarisson.home

import com.presentation.ui.home.utils.formatAgency
import com.presentation.ui.home.utils.dateFormat
import com.presentation.ui.home.utils.formatToMonetary
import org.junit.Assert
import org.junit.Test

class HomeExtensionTest {
    @Test
    fun formatAgency() {
        val agency = "01111224"
        Assert.assertEquals(agency.formatAgency(), "01.11122-4")
    }

    @Test
    fun formatToMonetary() {
        val money = 1000.3332
        Assert.assertEquals(money.formatToMonetary(), "R$ 1000,33")
    }

    @Test
    fun formatDateString() {
        val date = "2020-04-29"
        Assert.assertEquals(date.dateFormat(), "29/04/2020")
    }
}