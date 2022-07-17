package com.example.pokemon.di

import android.app.Application
import androidx.room.Room
import com.example.pokemon.db.PokemonDB
import com.example.pokemon.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provide_pokemon_database(application: Application): PokemonDB =
            Room.databaseBuilder(application, PokemonDB::class.java, "pokemon_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        @JvmStatic
        @Provides
        @Singleton
        fun provide_pokemon_dao(pokemon_db: PokemonDB): PokemonDao =
            pokemon_db.pokemon_dao()
    }
}