package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Plural

fun TimeUnits.plural(value: Int):String{
    return Plural.getPlural(value,this)
}