package com.shoppi.app.repository.productdetail

import com.shoppi.app.model.Product

class ProductDetailRepository(
    private val remoteDataSource: ProductDetailDataSource
) {
    suspend fun getProduct(productId: String): Product {
        return remoteDataSource.getProduct(productId)
    }
}