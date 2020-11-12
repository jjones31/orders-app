package com.redhat.bob.amex.orders.service

import com.redhat.bob.amex.orders.infra.messaging.OrderFailed
import com.redhat.bob.amex.orders.infra.messaging.OrderStatusNotification
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

class TestObserver(val test: (event: OrderStatusNotification) -> Unit) : Observer {
    override fun update(o: Observable?, arg: Any?) {
        when(o) {
            is NotificationService -> {
                if (arg is OrderStatusNotification) {
                    test(arg)
                }
            }
        }
    }
}

internal class OrderServiceTest {

    private val notificationService = NotificationService()
    private val orderService = OrderService(notificationService = notificationService)

    @Test
    fun shouldBe205() {
        notificationService.addObserver(TestObserver {

            println(it)

            assertEquals(2.05, it.total)
            assertEquals(1.45, it.discountedTotal)
        })
        orderService.submitOrder(listOf("apple", "apple", "orange", "apple"))
    }

    @Test
    fun shouldBe0() {
        notificationService.addObserver(TestObserver {
            assertEquals(0.0, it.total)
            assertEquals(0.0, it.discountedTotal)
        })
        orderService.submitOrder(listOf())
    }

    @Test
    fun shouldBe0IfItemIsUnknown() {
        notificationService.addObserver(TestObserver {
            assertEquals(0.0, it.total)
            assertEquals(0.0, it.discountedTotal)
        })
        orderService.submitOrder(listOf("carrot"))
    }

    @Test
    fun shouldBe60() {
        notificationService.addObserver(TestObserver {
            assertEquals(0.60, it.total)
            assertEquals(0.60, it.discountedTotal)
        })
        orderService.submitOrder(listOf("apple"))
    }

    @Test
    fun shouldBe25() {
        notificationService.addObserver(TestObserver {
            assertEquals(0.25, it.total)
            assertEquals(0.25, it.discountedTotal)
        })
        orderService.submitOrder(listOf("orange"))
    }
}