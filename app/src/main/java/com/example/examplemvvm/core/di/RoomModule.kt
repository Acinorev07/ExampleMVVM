package com.example.examplemvvm.core.di

import android.content.Context
import androidx.room.Room
import com.example.examplemvvm.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"

    // Creamos o inyectamos la base de datos
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        QuoteDatabase::class.java,
        QUOTE_DATABASE_NAME
    ).build()

    //Creamos o inyectamos el dao
    @Singleton
    @Provides
    fun provideoQuoteDao(db:QuoteDatabase) = db.getQuoteDao()


}