package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?):Pair<String?, String?>{

        val parts:List<String>? = fullName?.split(" ")

        var firstName:String? = null
        var lastName:String? = null

        if (parts?.getOrNull(0) != "")
            firstName = parts?.getOrNull(0)
        if (parts?.getOrNull(1) !="")
            lastName = parts?.getOrNull(1)

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider:String = " "): String {
        val mapping = payload.map {
            when(it){
               ' '->""
                'а'-> 'a'
                'б' -> 'b'
                'в' -> 'v'
                'г' -> 'g'
                'д' -> 'd'
                'е' -> 'e'
                'ё' -> 'e'
                'ж' -> "zh"
                'з' -> 'z'
                'и' -> 'i'
                'й' -> 'i'
                'к' -> 'k'
                'л' -> 'l'
                'м' -> 'm'
                'н' -> 'n'
                'о' -> 'o'
                'п' -> 'p'
                'р' -> 'r'
                'с' -> 's'
                'т' -> 't'
                'у' -> 'u'
                'ф' -> 'f'
                'х' -> 'h'
                'ц' -> 'c'
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> 'i'
                'ь' -> ""
                'э' -> 'e'
                'ю' -> "yu"
                'я' -> "ya"
                'А' -> 'A'
                'Б' -> 'B'
                'В' -> 'V'
                'Г' -> 'G'
                'Д' -> 'D'
                'Е' -> 'E'
                'Ё' -> 'E'
                'Ж' -> "Zh"
                'З' -> 'Z'
                'И' -> 'I'
                'Й' -> 'I'
                'К' -> 'K'
                'Л' -> 'L'
                'М' -> 'M'
                'Н' -> 'N'
                'О' -> 'O'
                'П' -> 'P'
                'Р' -> 'R'
                'С' -> 'S'
                'Т' -> 'T'
                'У' -> 'U'
                'Ф' -> 'F'
                'Х' -> 'H'
                'Ц' -> 'C'
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ъ' -> ""
                'Ы' -> 'I'
                'Ь' -> ""
                'Э' -> 'E'
                'Ю' -> "Yu"
                'Я' -> "Ya"
                else -> it
            }
        }

        return mapping.joinToString(separator = "")
    }

    fun toInitials(firstName: String?, lastName:String?): String? {
        var output: String?
        if ((firstName?.replace(" ", "") == "" && lastName?.replace(" ", "") == "") ||
            (firstName == null && lastName == null))
            return ""
        else
            return "${firstName?.replace(" ", "")?.getOrNull(0) ?: ""} ${lastName?.replace(" ", "")?.getOrNull(0)
                ?: ""}".toUpperCase()
    }

    fun verification(github: String) = Regex(
        "((https://|www.|https://www.)?github.com/(?!enterprise$|features$|topics$|collections$|trending$|events$|marketplace$|pricing$|nonprofit$|customer-stories$|security$|login$|join$)[\\w\\d-_]{1,39}/?$)|").find(github)?.value == github



}