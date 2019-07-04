package ru.skillbranch.devintensive.extensions

import android.text.Html

fun String.truncate(length:Int=16):String{
    //Todo check to count +1
    var res = this.substring(0,length+1)
    if (res[res.length-1]==' ')
        res=res.dropLast(1)
    return res.plus("...")
}
fun String.stripHtml():String{
    var tags = """(<[^<>]*>)|(  +)|([&<>'"])"""
    val regex = Regex(  tags)

    return  regex.replace(this, "") // result: abxcdxef


}