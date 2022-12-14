package com.example.coolwheatherapp.di

import com.example.coolwheatherapp.data.network.ApiService
import com.example.coolwheatherapp.util.Constatnts
import com.squareup.moshi.Moshi.Builder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
@Provides
@Singleton
fun provideApi(builder:Retrofit.Builder):ApiService{
    return builder
        .build()
        .create(ApiService::class.java)

}
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(Constatnts.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())

    }




}