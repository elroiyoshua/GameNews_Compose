package com.example.gamenews_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamenews_compose.repository.GamesRepository
import com.example.gamenews_compose.ui.theme.screen.detail.DetailViewModel
import com.example.gamenews_compose.ui.theme.screen.home.HomeViewModel

class ViewModelFactory(private val repository: GamesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T

        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}