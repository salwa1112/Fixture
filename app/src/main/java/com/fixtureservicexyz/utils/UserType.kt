package com.fixtureservicexyz.utils

object UserType {
    private var userType: UserEnum? = null
    fun setUserType(userType: UserEnum) {
        this.userType = userType
    }

    fun getUserType() = this.userType
}

enum class UserEnum {
    USER,
    GUEST,
    GMAIL
}