package com.presentation.ui.home.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.dateFormat() : String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(parser.parse(this))
}

fun String.formatAgency(): String{
    val pattern = "^(\\d{0,2})(\\d{0,5})(\\d{0,9})$".toRegex()
    return pattern.replace(this, "$1.$2-$3")
}

fun Double.formatToMonetary() : String =
    DecimalFormat("R$ 0.00").format(this).replace(".", ",")

