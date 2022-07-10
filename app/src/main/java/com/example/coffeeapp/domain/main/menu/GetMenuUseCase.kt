package com.example.coffeeapp.domain.main.menu

import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(id: Int) = menuRepository(id)
}