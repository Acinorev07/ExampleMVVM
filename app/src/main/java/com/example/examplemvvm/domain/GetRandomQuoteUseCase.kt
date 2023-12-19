package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel

import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject


class GetRandomQuoteUseCase @Inject constructor(
    private val repository:QuoteRepository
){


   suspend operator fun invoke(): Quote? {

        //Vamos a llamar al quote provider que es donde estan almacenadas las citas obtenidas de internet, en dado
        //caso que no se hallan almacenado citas en el, debuelve una lista vacia
        val quotes: List<Quote> = repository.getAllQuotesFromDatabase()

        //Ahora si la lista no es nula, ni vacia, devolvera un objeto del listado

        if (!quotes.isNullOrEmpty()) {
            //Ahora, para devolver un numero de citas aleatorio dependiendo del tamaño de la lista
            //hacemos
            val randomNumber = (quotes.indices).random()

            return quotes[randomNumber]


        }
        return null
    }

}