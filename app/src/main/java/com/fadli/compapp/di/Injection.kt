package com.fadli.compapp.di

import com.fadli.compapp.data.HeroRepository


object Injection {
    fun provideRepository(): HeroRepository {
        return HeroRepository.getInstance()
    }
}