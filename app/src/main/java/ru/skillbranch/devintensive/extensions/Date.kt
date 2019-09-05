package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 *  HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.shortFormat(): String {
    val parent = if (this.isSameDay(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(parent, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.isSameDay(date: Date): Boolean {
    val day1 = this.time/ DAY
    val day2 = date.time/ DAY
    return day1 == day2
}

fun Date.add(value: Int, units:TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time +=when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY

    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date=Date()):String {
   val raznica = (date.time - this.time)
    return if (raznica >= 0){
        when(raznica){
            in 0L * SECOND..1L * SECOND -> "только что"
            in 1L * SECOND..45L * SECOND -> "несколько секунд назад"
            in 45L * SECOND..75L * SECOND -> "минуту назад"
            in 75L * SECOND..45L * MINUTE -> "${raznica/ MINUTE} ${getNumForm("минуту;минуты;минут", raznica/ MINUTE)} назад"
            in 45L * MINUTE..75L * MINUTE -> "час назад"
            in 75L * MINUTE..22L * HOUR -> "${raznica/ HOUR} ${getNumForm("час;часа;часов", raznica/ HOUR)} назад"
            in 22L * HOUR..26L * HOUR -> "день назад"
            in 26L * HOUR..360L * DAY -> "${raznica/ DAY} ${getNumForm("день;дня;дней", raznica/ DAY)} назад"
            else -> "более года назад"
        }
    }
    else{
        val absraznica = abs(raznica)
        when (absraznica){
            in 0L * SECOND..1L * SECOND -> "только что"
            in 1L * SECOND..45L * SECOND -> "через несколько секунд"
            in 45L * SECOND..75L * SECOND -> "через минуту"
            in 75L * SECOND..45L * MINUTE -> "через ${absraznica/ MINUTE} ${getNumForm("минуту;минуты;минут", absraznica/ MINUTE)}"
            in 45L * MINUTE..75L * MINUTE -> "через час"
            in 75L * MINUTE..22L * HOUR -> "через ${absraznica/ HOUR} ${getNumForm("час;часа;часов", absraznica/ HOUR)}"
            in 22L * HOUR..26L * HOUR -> "через день"
            in 26L * HOUR..360L * DAY -> "через ${absraznica/ DAY} ${getNumForm("день;дня;дней", absraznica/ DAY)}"
            else -> "более чем через год"
        }
    }
}

private fun getNumForm(pluralForms:String, value: Long):String{
    val forms = pluralForms.split(";")
    when (value % 10){
        1L -> if (value % 100L != 11L)
            return forms[0]
        2L, 3L, 4L -> return if (value % 100 !in 12..14)
            forms[1]
        else forms[2]
    }
    return forms[2]
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
