package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage (  id:String,
                     chat: Chat?,
                     from:User,
                     isIncoming: Boolean=false,
                     date: Date = Date(),
                     var text: String
):BaseMessage(id,chat,from,isIncoming,date){
    override fun formatMessage(): String = "id:$id from:${from.firstName}" +
            " ${if(isIncoming) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}