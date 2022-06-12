package com.example.coffeeapp.domain.main.storage

import com.example.coffeeapp.data.login.network.UserInfoDto

interface LoadStorage {
    operator fun invoke():UserInfoDto
}