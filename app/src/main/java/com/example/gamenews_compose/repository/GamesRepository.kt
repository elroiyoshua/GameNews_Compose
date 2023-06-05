package com.example.gamenews_compose.repository

import com.example.gamenews_compose.model.DummyGamesData
import com.example.gamenews_compose.model.OrderGames
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GamesRepository {
    private  val orderGames = mutableListOf<OrderGames>()

    init {
        if (orderGames.isEmpty()){
            DummyGamesData.dummyGames.forEach{
                orderGames.add(OrderGames(it,0))
            }
        }
    }
    fun getAllGames(): Flow<List<OrderGames>>{
        return flowOf(orderGames)
    }
    fun getGamesById(gamesId:Long):OrderGames{
        return orderGames.first {
            it.games.id == gamesId
        }
    }

    companion object{
        @Volatile
        private var instance : GamesRepository ?= null

        fun getInstance(): GamesRepository = instance ?: synchronized(this){
            GamesRepository().apply {instance = this }
        }
    }
}