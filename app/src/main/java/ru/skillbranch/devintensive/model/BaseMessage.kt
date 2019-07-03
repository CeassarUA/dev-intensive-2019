package ru.skillbranch.devintensive.model

import java.util.*

abstract class BaseMessage (
    val id:String,
    val from:User?,
    val chat:Chat,
    val isIncoming:Boolean = false,
    val date: Date = Date()
){
    abstract fun formatMessage():String
    companion object AbstractFactory{
        var lasId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date= Date(),
                        type:String="text", payload: Any?, isIncoming: Boolean = false)
                :BaseMessage{
            lasId++
            return when(type){
                "image"->ImageMessage("$lasId", from, chat, date = date,image = payload as String)
                else -> TextMessage("$lasId", from, chat, date = date, text = payload as String)
            }
        }
    }
}