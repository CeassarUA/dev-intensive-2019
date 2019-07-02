package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (
    val id:String,
    val chat: Chat?,
    val from:User,
    val isIncoming: Boolean=false,
    val date:Date = Date()

){
    abstract fun formatMessage():String
    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(from: User,chat: Chat?, date:Date=Date(),type:BaseMessage.Type,payload:Any?):BaseMessage{
            lastId++
            return when(type){
                Type.TEXT-> TextMessage("$lastId",from = from,chat = chat,date = date,text =  payload as String)
                Type.IMAGE-> ImageMessage("$lastId",from = from,chat = chat,date = date,image =  payload as String)
            }
        }
    }
    enum class Type{
        TEXT,
        IMAGE
    }
}