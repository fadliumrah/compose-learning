package com.fadli.compapp.data

import com.fadli.compapp.model.FakeHeroDataSource
import com.fadli.compapp.model.OrderHero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HeroRepository {

    private val orderHeroes = mutableListOf<OrderHero>()

    init {
        if (orderHeroes.isEmpty()) {
            FakeHeroDataSource.dummyHeroes.forEach {
                orderHeroes.add(OrderHero(it, 0))
            }
        }
    }

    fun getAllHeroes(): Flow<List<OrderHero>> {
        return flowOf(orderHeroes)
    }

    fun getOrderHeroesById(heroId: Long): OrderHero {
        return orderHeroes.first {
            it.hero.id == heroId
        }
    }



    companion object {
        @Volatile
        private var instance: HeroRepository? = null

        fun getInstance(): HeroRepository =
            instance ?: synchronized(this) {
                HeroRepository().apply {
                    instance = this
                }
            }
    }
}