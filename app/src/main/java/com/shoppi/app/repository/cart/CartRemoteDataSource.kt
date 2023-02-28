package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartProduct
import com.shoppi.app.network.ApiClient

class CartRemoteDataSource(private val api: ApiClient): CartDataSource {
    override suspend fun getCartProduct(): CartProduct {
        return api.getCartProduct()
    }
}