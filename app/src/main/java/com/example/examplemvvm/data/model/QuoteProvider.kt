package com.example.examplemvvm.data.model

import javax.inject.Inject
import javax.inject.Singleton
/*
@Singleton
class QuoteProvider @Inject constructor() {
        var quotes: List<QuoteModel> = emptyList()
}*/

//Esta clase ya no se requiere porque actuaba como una cache local para almacenar los datos obtenidos de internet
//pero ahora ese papel lo tiene room asi que esta clase debe ser ignorada, solo se deja con fines academicos