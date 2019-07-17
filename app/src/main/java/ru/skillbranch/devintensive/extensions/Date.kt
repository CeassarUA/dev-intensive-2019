package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val timeDiffMillis = this.time - date.time
    val timeDif = object {
        var min: Int = Math.abs(timeDiffMillis / MINUTE).toInt()
        var h: Int = Math.abs(timeDiffMillis / HOUR).toInt()
        var days: Int = Math.abs(timeDiffMillis / DAY).toInt()
    }

    return when (Math.abs(timeDiffMillis)) {
        0 * SECOND, 1 * SECOND -> "только что"

        in 2 * SECOND..45 * SECOND -> if (timeDiffMillis > 0) "через несколько секунд" else "несколько секунд назад"

        in 45 * SECOND..75 * SECOND -> if (timeDiffMillis > 0) "через минуту" else "минуту назад"

        in 75 * SECOND..45 * MINUTE -> if (timeDiffMillis > 0)
            "через ${TimeUnits.MINUTE.plural(timeDif.min)}"
        else "${TimeUnits.MINUTE.plural(timeDif.min)} назад"

        in 45 * MINUTE..75 * MINUTE -> if (timeDiffMillis > 0) "через час" else "час назад"

        in 75 * MINUTE..22 * HOUR -> if (timeDiffMillis > 0)
            "через ${TimeUnits.HOUR.plural(timeDif.h)}" else
            "${TimeUnits.HOUR.plural(timeDif.h)} назад"

        in 22 * HOUR..26 * HOUR -> if (timeDiffMillis > 0) "через день" else "день назад"

        in 26 * HOUR..360 * DAY -> if (timeDiffMillis > 0)
            "через ${TimeUnits.DAY.plural(timeDif.days)}" else
            "${TimeUnits.DAY.plural(timeDif.days)} назад"

        else -> if (timeDiffMillis > 0) "более чем через год" else "более года назад"

    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val r = value % 10
        val r2 = value % 100

        return "$value " + when (this) {
            SECOND -> when (r) {
                1 -> if (r2 in 11..19) "секунд" else "секунду"
                2, 3, 4 -> if (r2 in 11..19) "секунд" else "секунды"
                else -> "секунд"
            }
            MINUTE -> when (r) {
                1 -> if (r2 in 11..19) "минут" else "минуту"
                2, 3, 4 -> if (r2 in 11..19) "минут" else "минуты"
                else -> "минут"
            }
            HOUR -> when (r) {
                1 -> if (r2 in 11..19) "часов" else "час"
                2, 3, 4 -> if (r2 in 11..19) "часов" else "часа"
                else -> "часов"
            }
            DAY -> when (r) {
                1 -> if (r2 in 11..19) "дней" else "день"
                2, 3, 4 -> if (r2 in 11..19) "дней" else "дня"
                else -> "дней"
            }
        }
    }
}