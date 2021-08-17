package com.biscuitkid.carousellnews.utils


fun getDateAgo(date: Int): String {
    val diff = (System.currentTimeMillis() / 1000L) - date

    val oneSec = 1L
    val oneMin: Long = 60 * oneSec
    val oneHour: Long = 60 * oneMin
    val oneDay: Long = 24 * oneHour
    val oneWeek: Long = 7 * oneDay
    val oneMonth: Long = 30 * oneDay
    val oneYear: Long = 365 * oneDay

    val diffMin: Long = diff / oneMin
    val diffHours: Long = diff / oneHour
    val diffDays: Long = diff / oneDay
    val diffWeeks: Long = diff / oneWeek
    val diffMonths: Long = diff / oneMonth
    val diffYears: Long = diff / oneYear


    return if (diffYears > 0) {
        " $diffYears years ago"
    } else if (diffMonths > 0 && diffYears < 1) {
        " ${(diffMonths - diffYears / 12)} months ago "
    } else if (diffWeeks > 0 && diffMonths < 1) {
        " ${(diffWeeks - diffMonths / 30)} weeks ago "
    } else if (diffDays > 0 && diffWeeks < 1) {
        " ${(diffDays - diffMonths / 30)} days ago "
    } else if (diffHours > 0 && diffDays < 1) {
        " ${(diffHours - diffDays * 24)} hours ago "
    } else if (diffMin > 0 && diffHours < 1) {
        " ${(diffMin - diffHours * 60)} min ago "
    } else {
        " just now"
    }
}