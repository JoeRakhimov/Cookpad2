package com.joerakhimov.cookpad2.util.time

import java.text.SimpleDateFormat

const val DATETIME_FORMAT_BACKEND = "yyyy-MM-dd'T'HH:mm:ssZ"
const val DATE_FORMAT_HUMAN = "dd LLLL yyyy"

class DateTimeUtil {

    // input: 2021-12-22T04:53:00+00:00
    // output: 22 December 2021
    fun fromBackendDatetimeToHumanReadableDate(input: String): String {
        val date = SimpleDateFormat(DATETIME_FORMAT_BACKEND).parse(input)
        return SimpleDateFormat(DATE_FORMAT_HUMAN).format(date)
    }

}