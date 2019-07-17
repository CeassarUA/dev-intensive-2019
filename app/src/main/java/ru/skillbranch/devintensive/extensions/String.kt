package ru.skillbranch.devintensive.extensions

fun String.stripHtml(): String {
    return this
        .replace(Regex("(?:<[^>]*>)|(?:&[#a-z0-9]+;)"), "")
        .replace(Regex(" +"), " ")
}

fun String.truncate(count: Int = 16): String {
    val trimmed = this.trim()
    return if (trimmed.length > count) trimmed.substring(0, count).trimEnd().plus("...") else trimmed
}