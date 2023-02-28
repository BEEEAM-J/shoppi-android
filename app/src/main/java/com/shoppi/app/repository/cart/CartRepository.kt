package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartProduct

class CartRepository(
    private val dataSource: CartRemoteDataSource
) {
    suspend fun getCartProduct(): CartProduct {
        return dataSource.getCartProduct()
    }
}