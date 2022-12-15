package com.example.coolwheatherapp.di

import com.example.coolwheatherapp.data.network.QuotesApiService
import com.example.coolwheatherapp.data.network.WeatherApiService
import com.example.coolwheatherapp.util.Constatnts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
@Provides
@Singleton
fun provideApi(@Named("weather") builder:Retrofit.Builder):WeatherApiService{
    return builder
        .build()
        .create(WeatherApiService::class.java)

}
    @Provides
    @Singleton
    @Named("weather")
     fun provideRetrofit():Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(Constatnts.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())

    }
    @Provides
    @Singleton
    fun provideQuotesApi(@Named("quotes") builder:Retrofit.Builder):QuotesApiService{
        return builder
            .build()
            .create(QuotesApiService::class.java)

    }
    @Provides
    @Singleton
    @Named("quotes")
    fun provideQuoteRetrofit():Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(Constatnts.QUOTES_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())

    }


}