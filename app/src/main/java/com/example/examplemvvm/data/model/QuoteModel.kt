package com.example.examplemvvm.data.model

import com.example.examplemvvm.domain.model.Quote
import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("quote")
    val quote:String,
    @SerializedName("author")
    val author:String
)


