package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity

import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    //private val quoteProvider:QuoteProvider
    private val quoteDao: QuoteDao

) {

    suspend fun getAllQuotesFromApi(): List<Quote>{
        //Aqui llamamos al backend para recuperar las citas
        val response = api.getQuotes()

        //Luego el QuoteProvider actuando como una pequeña base de datos, guarda la response en el
        //quoteProvider.quotes = response

        //Luego devolvemos la respuesta
        return response.map{it.toDomain()}
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote>{
        val response = quoteDao.getAllQuotes()

        return response.map{it.toDomain()}
    }

    //Aqui creamos el metodo que recibe un listado de QuoteEntity, porque necesitamos insertar entidades
    // en la base de datos.
    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    //Esta funcion llama a la funcion en el quoteDao para borrar la tabla de Room
    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }

}