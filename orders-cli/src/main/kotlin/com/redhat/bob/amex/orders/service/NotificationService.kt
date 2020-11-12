package com.redhat.bob.amex.orders.service

import com.redhat.bob.amex.orders.infra.messaging.OrderCompleted
import com.redhat.bob.amex.orders.infra.messaging.OrderFailed
import com.redhat.bob.amex.orders.infra.messaging.OrderStatusNotification
import java.time.LocalDate
import java.util.*

/**
 * Service that facilitates propagating notifications to clients.
 */
class NotificationService : Observer, Observable() {

    fun notifyClientsOfOutOfStock(event: OrderFailed) {

        // Add 10 days from today as fake shipping calculations
        setChanged()
        notifyObservers(event)
    }

    fun notifyClients(event: OrderCompleted) {

        // Add 10 days from today as fake shipping calculations
        setChanged()
        notifyObservers(OrderStatusNotification(LocalDate.now().plusDays(10),
            "shipping", event.total, event.totalDiscount, event.discountedTotal))
    }

    override fun update(o: Observable?, arg: Any?) {
        when(o) {
            is OrderService -> {
                if (arg is OrderCompleted) {
                    notifyClients(arg)
                } else if (arg is OrderFailed) {
                    notifyClientsOfOutOfStock(arg)
                }
            }
        }
    }
}