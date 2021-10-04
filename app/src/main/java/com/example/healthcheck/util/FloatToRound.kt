package com.example.healthcheck.util

fun Float.round(decimals: Int = 1): String =
    "%.${decimals}f".format(this)