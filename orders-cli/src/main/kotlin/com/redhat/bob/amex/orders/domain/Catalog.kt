package com.redhat.bob.amex.orders.domain

/**
 * Contains the store of products and related information.
 */
class Catalog {

    // create the product inventory for the catalog
    // NOTE: Using USD CENTS to represent the price. This is a quick and dirty way of
    //       avoiding the precision issues when handling currency/doubles.
    private var inventory: List<Product> = listOf(Product("orange", 25),
        Product("apple", 60))

    fun findByName(name: String): Product =
        inventory.first { name.toUpperCase() == it.name.toUpperCase() }.copy()

}