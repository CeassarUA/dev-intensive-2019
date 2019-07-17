package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")

        val firstName = parts?.getOrNull(0)?.ifBlank { null }
        val lastName = parts?.getOrNull(1)?.ifBlank { null }
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        for (letter in payload) {
            result += when (letter) {
                'А' -> "A"
                'а' -> "a"
                'Б' -> "B"
                'б' -> "b"
                'В' -> "V"
                'в' -> "v"
                'Г' -> "G"
                'г' -> "g"
                'Д' -> "D"
                'д' -> "d"
                'Е', 'Ё' -> "E"
                'е', 'ё' -> "e"
                'Ж' -> "Zh"
                'ж' -> "zh"
                'З' -> "Z"
                'з' -> "z"
                'И', 'Й' -> "I"
                'и', 'й' -> "i"
                'К' -> "K"
                'к' -> "k"
                'Л' -> "L"
                'л' -> "l"
                'М' -> "M"
                'м' -> "m"
                'Н' -> "N"
                'н' -> "n"
                'О' -> "O"
                'о' -> "o"
                'П' -> "P"
                'п' -> "p"
                'Р' -> "R"
                'р' -> "r"
                'С' -> "S"
                'с' -> "s"
                'Т' -> "T"
                'т' -> "t"
                'У' -> "U"
                'у' -> "u"
                'Ф' -> "F"
                'ф' -> "f"
                'Х' -> "H"
                'х' -> "h"
                'Ц' -> "C"
                'ц' -> "c"
                'Ч' -> "Ch"
                'ч' -> "ch"
                'Ш' -> "Sh"
                'ш' -> "sh"
                'Щ' -> "Sh'"
                'щ' -> "sh'"
                'Ъ', 'ъ' -> ""
                'Ы' -> "Y"
                'ы' -> "y"
                'Ь', 'ь' -> ""
                'Э' -> "E"
                'э' -> "e"
                'Ю' -> "Yu"
                'ю' -> "yu"
                'Я' -> "Ya"
                'я' -> "ya"
                ' ' -> divider
                else -> letter
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fetchFirstLetter = { s: String? -> s?.trim()?.getOrNull(0)?.toUpperCase() ?: "" }
        return "${fetchFirstLetter(firstName)}${fetchFirstLetter(lastName)}".ifBlank { null }
    }
}