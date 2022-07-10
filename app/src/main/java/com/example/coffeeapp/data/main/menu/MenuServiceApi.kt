package com.example.coffeeapp.data.main.menu

import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface MenuServiceApi {

    @GET("/location/{id}/menu")
    suspend operator fun invoke(@Path("id") id: Int?): ArrayList<ShopMenu>

    class FakeMenuService @Inject constructor() : MenuServiceApi {
        private val shopMenList = listOf(
            ShopMenu(
                1, "Колд Брю",
                "https://vseprocofe.ru/images/1/retsepti-kofe-E74E1.jpg", 100.0
            ),
            ShopMenu(
                2,
                "Блонд Пур Овер Толл",
                "https://t4.ftcdn.net/jpg/02/68/73/65/240_F_268736548_Y8KSMQoAy0Tam161GhhfrRXUQcPrNMGl.jpg",
                200.0
            ),
            ShopMenu(
                3,
                "Кения Пур Овер Шорт",
                "https://vacationidea.com/pix/img25Hy8R/articles/best-oklahoma-city-coffee-shops_t.jpg",
                160.0
            ),
            ShopMenu(
                4,
                "Суматра Пур Овер Венти",
                "https://data.parkbench.com/content/data/events/3/0/7/1/572773/1473481703_577783.jpg",
                300.0
            ),
            ShopMenu(
                5,
                "Эспрессо Роаст Декаф Кловер Шорт",
                "https://primaveraclub.ru/wp-content/uploads/2020/10/1251089_1585043604.1016_original.jpg",
                120.0
            ),
            ShopMenu(
                6,
                "Гватемала Антигуа Пур Овер Шорт",
                "https://jamaicamocha.files.wordpress.com/2018/10/dsc_0532.jpg?w=5984",
                210.0
            ),
            ShopMenu(
                7,
                "Верона Пур Овер Толл ",
                "https://ae04.alicdn.com/kf/H83a43d310e6f40149754150d311d3f2aL/500-Unbleached.jpg",
                190.0
            ),
            ShopMenu(
                8,
                "Суматра Пур Овер Венти",
                "https://data.parkbench.com/content/data/events/3/0/7/1/572773/1473481703_577783.jpg",
                350.0
            ),
            ShopMenu(
                9,
                "Кения Пур Овер Шорт",
                "https://vacationidea.com/pix/img25Hy8R/articles/best-oklahoma-city-coffee-shops_t.jpg",
                222.0
            ),
            ShopMenu(
                10,
                "Чача Пур Овер Самогон",
                "https://edaplus.info/drinks/images/moonshine.png",
                300.0
            )
        )

        override suspend fun invoke(id: Int?): ArrayList<ShopMenu> {
            return ArrayList(shopMenList.shuffled().take((1..6).random()))
        }
    }
}