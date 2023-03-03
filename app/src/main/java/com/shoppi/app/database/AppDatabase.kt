package com.shoppi.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shoppi.app.model.CartItem

@Database(entities = [CartItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao
}