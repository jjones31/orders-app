package com.redhat.bob.amex.orders.domain

// Create OfferHandler lambda typealias to generate the discount from an order
typealias OfferHandler = (order: Order) -> Int

class Offers {

    // A list of all discount handlers to process the order
    private val discountHandlers = mutableListOf<OfferHandler>(::orangeOffer, ::appleBuyOneGetOne)

    fun handleDiscounts(order: Order): Int {
        var totalDiscount = 0

        // Execute each OfferHandler and add any discounts to the total discount.
        discountHandlers.forEach { handler -> totalDiscount += handler(order)}

        return totalDiscount
    }

    // OfferHandler for buying 3 oranges, get one free.
    private fun orangeOffer(order: Order) : Int {
        val numberOfOranges = order.items.filter { it == Catalog.ORANGE }.count()
        val numberOfDiscounts = numberOfOranges / 3
        return numberOfDiscounts * Catalog.ORANGE.price
    }

    // OfferHandler for buying 2 apples, get one free.
    private fun appleBuyOneGetOne(order: Order) : Int {
        val numberOfApples = order.items.filter { it == Catalog.APPLE }.count()
        val numberOfDiscounts = numberOfApples / 2
        return numberOfDiscounts * Catalog.APPLE.price
    }
}
