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
        fun makeMessage(from: User,chat: Chat?, date:Date=Date(),type:Type,payload:Any?,isIncoming: Boolean=false):BaseMessage{
            lastId++
            return when(type){
                Type.TEXT-> TextMessage("$lastId",from = from,chat = chat,date = date,text =  payload as String,isIncoming = isIncoming)
                Type.IMAGE-> ImageMessage("$lastId",from = from,chat = chat,date = date,image =  payload as String)
            }
        }
        fun makeMessage(from: User,chat: Chat?, date:Date=Date(),type:String,payload:Any?, isIncoming: Boolean=false):BaseMessage{

            return makeMessage(from, chat, date,if (type=="text") Type.IMAGE else Type.IMAGE,payload,isIncoming)

        }
    }
    enum class Type{
        TEXT,
        IMAGE
    }
}