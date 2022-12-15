package com.example.coolwheatherapp.repository

import android.util.Log
import com.example.coolwheatherapp.data.model.Quotes.SingleRandomQuote
import com.example.coolwheatherapp.data.network.QuotesApiService
import javax.inject.Inject

class QuoteRepo @Inject constructor(
    private val quotesApiService: QuotesApiService
){
    suspend fun getRandomQuote(): SingleRandomQuote {
     Log.e("QUOTE",quotesApiService.getRandomQuote().quote.toString())
        return quotesApiService.getRandomQuote()
    }
}