package com.redhat.bob.amex.orders.service

import com.redhat.bob.amex.orders.domain.Catalog
import com.redhat.bob.amex.orders.domain.Offers
import com.redhat.bob.amex.orders.domain.Order
import com.redhat.bob.amex.orders.infra.messaging.OrderCompleted
import java.util.*
import kotlin.NoSuchElementException

/**
 * The OrderService is responsible for taking raw input, creating a valid order
 * and submitting the order.
 */
class OrderService(private val catalog: Catalog = Catalog(),
                   private val offers: Offers = Offers(),
                   notificationService: NotificationService
) : Observable() {

    init {
        addObserver(notificationService)
    }

    fun submitOrder(items: List<String>?) {

        var total = 0.0
        val order = buildValidOrder(items)

        // Calculate the total from the valid order.
        order.items.forEach {
            total += it.price
        }

        // handle the discounts
        val totalDiscounts = offers.handleDiscounts(order)

        // subtract all discounts from total
        val discountedTotal = total - totalDiscounts

        // emit the event to subscribers/observers
        setChanged()
        notifyObservers(OrderCompleted(order, total / 100.0,
            discountedTotal / 100.0, totalDiscounts / 100.0))
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