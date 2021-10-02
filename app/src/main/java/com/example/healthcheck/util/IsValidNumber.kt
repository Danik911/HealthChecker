package com.example.healthcheck.util





fun String.isValidNumber(): Boolean {
   return this.matches("-?\\d+(\\.\\d+)?".toRegex()) && this.toFloat() > 0
}