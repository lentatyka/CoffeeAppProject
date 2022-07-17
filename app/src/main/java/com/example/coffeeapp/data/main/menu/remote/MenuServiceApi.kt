package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.data.main.menu.model.MenuItem
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface MenuServiceApi {

    @GET("/location/{id}/menu")
    suspend operator fun invoke(@Path("id") id: Long?): ArrayList<MenuItem>

    class FakeMenuService @Inject constructor() : MenuServiceApi {

        override suspend fun invoke(id: Long?): ArrayList<MenuItem> {
            return ArrayList(shopMenList.shuffled().take((2..9).random()))
        }

        private val shopMenList = listOf(
            MenuItem(
                1, "Колд Брю",
                "https://vseprocofe.ru/images/1/retsepti-kofe-E74E1.jpg", 100.0
            ),
            MenuItem(
                2,
                "Блонд Пур Овер Толл",
                "https://t4.ftcdn.net/jpg/02/68/73/65/240_F_268736548_Y8KSMQoAy0Tam161GhhfrRXUQcPrNMGl.jpg",
                200.0
            ),
            MenuItem(
                3,
                "Кения Пур Овер Шорт",
                "https://vacationidea.com/pix/img25Hy8R/articles/best-oklahoma-city-coffee-shops_t.jpg",
                160.0
            ),
            MenuItem(
                4,
                "Суматра Пур Овер Венти",
                "https://data.parkbench.com/content/data/events/3/0/7/1/572773/1473481703_577783.jpg",
                300.0
            ),
            MenuItem(
                5,
                "Эспрессо Роаст Декаф Кловер Шорт",
                "https://primaveraclub.ru/wp-content/uploads/2020/10/1251089_1585043604.1016_original.jpg",
                120.0
            ),
            MenuItem(
                6,
                "Гватемала Антигуа Пур Овер Шорт",
                "https://jamaicamocha.files.wordpress.com/2018/10/dsc_0532.jpg?w=5984",
                210.0
            ),
            MenuItem(
                7,
                "Верона Пур Овер Толл ",
                "https://ae04.alicdn.com/kf/H83a43d310e6f40149754150d311d3f2aL/500-Unbleached.jpg",
                190.0
            ),
            MenuItem(
                8,
                "Суматра Пур Овер Венти",
                "https://data.parkbench.com/content/data/events/3/0/7/1/572773/1473481703_577783.jpg",
                350.0
            ),
            MenuItem(
                9,
                "Кения Пур Овер Шорт",
                "https://vacationidea.com/pix/img25Hy8R/articles/best-oklahoma-city-coffee-shops_t.jpg",
                222.0
            ),
            MenuItem(
                10,
                "Чача Пур Овер Самогон",
                "https://edaplus.info/drinks/images/moonshine.png",
                300.0
            )
        )
    }
}