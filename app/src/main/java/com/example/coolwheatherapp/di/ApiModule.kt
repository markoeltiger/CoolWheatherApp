package com.example.coolwheatherapp.di

import com.example.coolwheatherapp.data.network.ApiService
import com.example.coolwheatherapp.util.Constatnts
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
    fun provideRetrofit(builder:Retrofit.Builder):Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(Constatnts.API_BASE_URL)
            .addCallAdapterFactory(MoshiConverterFactory.create())

    }




}