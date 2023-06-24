package ru.juxlab.ifoodshoptest.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.juxlab.ifoodshoptest.data.model.ProductList

interface APIGetProducts {

    @GET("latest.json")
    fun getProducts(): Deferred<ProductList>

    companion object{
        operator fun invoke(): APIGetProducts{
            return Retrofit.Builder()
                .client(
                    OkHttpClient()
                        .newBuilder()
                        .build())
                .baseUrl("https://run.mocky.io/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIGetProducts::class.java)
        }
    }
}