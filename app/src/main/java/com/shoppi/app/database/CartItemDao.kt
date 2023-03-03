package com.shoppi.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoppi.app.model.CartItem

// 데이터 추가, 조회, 삭제 요청
@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Query("SELECT * FROM cart_item")
    suspend fun load(): List<CartItem>
}