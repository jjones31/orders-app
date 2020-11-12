package com.redhat.bob.amex.orders.domain

/**
 * A value object that represents an order.
 *
 * The default value of the items in the order is an empty, mutable list of products
 */
data class Order (val items: MutableList<Product> = mutableListOf())