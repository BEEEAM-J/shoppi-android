package com.shoppi.app.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.CartItem
import com.shoppi.app.model.CartProduct
import com.shoppi.app.repository.cart.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartRepository
): ViewModel() {

    private val _items = MutableLiveData<List<CartItem>>()
    val items: LiveData<List<CartItem>> = _items

    fun loadCartProduct() {
        viewModelScope.launch {
            val cartItem = repository.getCartProduct()
            _items.value = cartItem
        }
    }
}