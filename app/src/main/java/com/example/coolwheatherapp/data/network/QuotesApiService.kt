package com.example.coolwheatherapp.data.network

import com.example.coolwheatherapp.data.model.Quotes.SingleRandomQuote
import com.example.coolwheatherapp.util.Constatnts
import retrofit2.http.GET

interface QuotesApiService {
@GET(Constatnts.RANDOM_QUOTES_ENDPOINT)
suspend fun getRandomQuote(

    ): SingleRandomQuote

}