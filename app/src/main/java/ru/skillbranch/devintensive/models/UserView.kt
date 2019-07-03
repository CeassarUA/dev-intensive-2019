package ru.skillbranch.devintensive.models

data class UserView(
    val id:String,
    val fullName:String,
    val nickName:String,
    val avatar:String?,
    val status:String,
    val initials:String?
) {

    fun printMe(){
        println("""
            id: $id
            fullName: $fullName
            nickName: $nickName
            avatar: $avatar
            status: $status
            initials: $initials
        """.trimIndent())
    }
}