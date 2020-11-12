package com.redhat.bob.amex.orders.service

import com.redhat.bob.amex.orders.domain.Catalog
import com.redhat.bob.amex.orders.domain.Offers
import com.redhat.bob.amex.orders.domain.Order

/**
 * The OrderService is responsible for taking raw input, creating a valid order
 * and submitting the order.
 */
class OrderService(private val catalog: Catalog = Catalog(),
                   private val offers: Offers = Offers()
) {

    fun submitOrder(items: List<String>?): Double {

        var total = 0.0
        val order = buildValidOrder(items)

        // Calculate the total from the valid order.
        order.items.forEach {
            total += it.price
        }

        // handle the discounts and subtract any from total
        total -= offers.handleDiscounts(order)

        // Convert the total into a value with 2 decimals.
        return total / 100.0
    }

    private fun buildValidOrder(items: List<String>?): Order {
        val order = Order()
        items?.forEach {
            try {
                // Ensure the item name is in the catalog.
                val product = catalog.findByName(it.trim())

                // Add the product to the order.
                order.items.add(product)
            } catch (e: NoSuchElementException) {
                // TODO: Figure out the business rule when product specified doesn't exist.
            }
        }
        return order
    }
}