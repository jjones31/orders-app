package com.redhat.bob.amex.orders.domain

/**
 * A value object representing a product with a name and price
 */
data class Product(val name: String, val price: Int, var quantity: Int) {
    fun remove(amount: Int) {
        if (quantity >= amount) {
            quantity -= amount
        } else {
            quantity = 0
        }
    }
}