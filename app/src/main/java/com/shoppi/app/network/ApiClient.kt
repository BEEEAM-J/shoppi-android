package com.shoppi.app.network

import com.shoppi.app.model.CartProduct
import com.shoppi.app.model.Category
import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.model.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("categories.json")
    suspend fun getCategories(): List<Category>

    @GET("fashion_female.json")
    suspend fun getCategoryDetail(): CategoryDetail

    @GET("products/{productId}.json")
    suspend fun getProducts(@Path("productId") productId: String): Product

    @GET("products.json")
    suspend fun getCartProduct(): CartProduct

//    @GET("{categoryId}.json")
//    suspend fun getCategoryDetail(@Path("categoryId") categoryId: String): CategoryDetail

    companion object {

        private const val baseUrl = "https://shoppi-acb19-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiClient::class.java)
        }
    }
}