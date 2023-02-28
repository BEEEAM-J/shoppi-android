package com.shoppi.app.model

import com.google.gson.annotations.SerializedName

sealed class CartProduct

data class CartHeader(
    @SerializedName("brand_name") val brandName: String
): CartProduct()

data class CartItem(
    @SerializedName("product_id") val productId: String,
    val label: String,
    val type: String,
    val price: Int,
    @SerializedName("thumbnail_image_url") val thumbnailImageUrl: String,
    @SerializedName("brand_name") val brandName: String,
    val amount: Int
): CartProduct()