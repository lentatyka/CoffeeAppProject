package com.example.coffeeapp.domain.main.menu

class GetMenuUseCase(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(id: Int) = menuRepository(id)
}