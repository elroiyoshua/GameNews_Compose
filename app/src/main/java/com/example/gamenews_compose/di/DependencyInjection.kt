package com.example.gamenews_compose.di

import com.example.gamenews_compose.repository.GamesRepository

object DependencyInjection {
    fun provideRepository():GamesRepository{
        return GamesRepository.getInstance()
    }
}