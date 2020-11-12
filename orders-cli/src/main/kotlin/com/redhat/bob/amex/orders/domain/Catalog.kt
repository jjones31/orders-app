package com.redhat.bob.amex.orders.domain

/**
 * Contains the store of products and related information.
 */
class Catalog {

    companion object {
        var APPLE: Product = Product("apple", 60)
        var ORANGE: Product = Product("orange", 25)
    }

    // create the product inventory for the catalog
    // NOTE: Using USD CENTS to represent the price. This is a quick and dirty way of
    //       avoiding the precision issues when handling currency/doubles.
    private var inventory: List<Product> = listOf(
        ORANGE,
        APPLE)

    fun findByName(name: String): Product =
        inventory.first { name.toUpperCase() == it.name.toUpperCase() }.copy()

}