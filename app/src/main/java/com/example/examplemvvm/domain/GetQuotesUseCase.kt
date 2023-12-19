package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.database.entities.toDatabase
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository:QuoteRepository
) {
    //En ves de crear una funcion normal haremos lo siguiente
    suspend operator fun invoke(): List<Quote>{

        val quotes = repository.getAllQuotesFromApi()

        //Aqui implementamos la logica para que el caso de uso sepa de donde obtener la lista de Quotes,
        //si la lista que viene desde internet esta vacia o no se obtiene, entonces,como siempre que se lanza
        //la app se guarda una copia de los datos en cada base, el repositorio puede obtener los datos de la tabla
        // procedemos de la siguiente manera

        return if (quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map{it.toDatabase()})
            quotes
        }else{
            //Le retornamos la version guardada en la base de datos
            repository.getAllQuotesFromDatabase()
        }
    }
}