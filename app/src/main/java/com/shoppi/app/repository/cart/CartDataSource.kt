package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartProduct

interface CartDataSource {
    suspend fun getCartProduct(): CartProduct
}