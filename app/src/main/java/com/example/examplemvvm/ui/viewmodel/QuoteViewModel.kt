package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.data.model.QuoteModel

import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase

): ViewModel() {
// El live data permite al activity suscribirse a un modelo de datos y que se llame automaticamente
    //cuando se realiza un cambio en dicho modelo
    val quoteModel = MutableLiveData<Quote?>()

    // Codigo para el progres bar
    val isLoading = MutableLiveData<Boolean>()

    //Como es un modelo de datos que va a ir cambiando se encapsula en un live data mutable


    fun onCreate() {
        viewModelScope.launch {
            // Si queremos que se muestre el progressBar
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    //Ahora creamos una funcion que le dice al liveData como va a ir cambiando el modelo de datos
    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val quote = getRandomQuoteUseCase()
            if (quote != null) {

                quoteModel.postValue(quote)
            }


            isLoading.postValue(false)


        }
    }

}


