package com.example.coffeeapp.data.network.login

import com.example.coffeeapp.domain.storage.UserInfo

data class UserInfoDto(
    val token: String?,
    val tokenLifeTime: Long
)

fun UserInfoDto.toUserInfo() = UserInfo(
    token = token,
    tokenLifeTime = tokenLifeTime
)
