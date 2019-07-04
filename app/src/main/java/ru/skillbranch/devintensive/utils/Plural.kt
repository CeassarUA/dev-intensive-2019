package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.TimeUnits

object Plural {

    fun getPlural(value:Int, timeUnits: TimeUnits):String{
        val rez = when(Math.abs(value)%10){
            1-> get1Plural(timeUnits)
            2-> get2Plural(timeUnits)
            4->get2Plural(timeUnits)
            else -> getManyPlural(timeUnits)

        }

        return "$value $rez"
    }
    private fun get1Plural(timeUnits: TimeUnits): String {
        return when (timeUnits) {
            TimeUnits.SECOND -> "секунду"
            TimeUnits.MINUTE -> "минуту"
            TimeUnits.HOUR -> "час"
            TimeUnits.DAY -> "день"
        }
    }

    private fun get2Plural(timeUnits: TimeUnits): String {
        return when (timeUnits) {
            TimeUnits.SECOND -> "секунды"
            TimeUnits.MINUTE -> "минуты"
            TimeUnits.HOUR -> "часа"
            TimeUnits.DAY -> "дня"
        }
    }

    private fun getManyPlural(timeUnits: TimeUnits): String {
        return when (timeUnits) {
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
    }
}