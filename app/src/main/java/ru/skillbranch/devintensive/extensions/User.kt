package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.ustils.Utils

fun User.toUserView():UserView{
    val nickName=Utils.transliterations(firstName,lastName)
    val initials="${firstName?.get(0)}${lastName?.get(0)}"
    val status = if(lastVisit==null)"ни разу не был" else if (isOnline) "Online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    return UserView(id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        avatar = avatar,
        initials = initials,
        status = status
        )
}