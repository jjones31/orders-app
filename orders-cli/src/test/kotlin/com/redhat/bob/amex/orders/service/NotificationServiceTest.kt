package com.redhat.bob.amex.orders.service

import com.redhat.bob.amex.orders.domain.Catalog
import com.redhat.bob.amex.orders.domain.Order
import com.redhat.bob.amex.orders.infra.messaging.OrderCompleted
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

internal class NotificationServiceTest {

    private val notificationService = NotificationService()

    @Test
    fun notifyClients() {
        notificationService.addObserver(TestObserver{
            assertEquals(1.25, it.total)
            assertEquals(1.00, it.discountedTotal)
            assertEquals(LocalDate.now().plusDays(10), it.shippingDate)
            assertEquals("shipping", it.status)
            assertEquals(0.25, it.totalDiscounts)
        })
        notificationService.notifyClients(OrderCompleted(Order(mutableListOf(Catalog.APPLE)),
            1.25, 1.00, 0.25))
    }
}