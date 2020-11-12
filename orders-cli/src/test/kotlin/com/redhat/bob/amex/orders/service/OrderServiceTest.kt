package com.redhat.bob.amex.orders.service

import com.redhat.bob.amex.orders.domain.Order
import com.redhat.bob.amex.orders.domain.Product
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OrderServiceTest {

    private val orderService = OrderService()

    @Test
    fun shouldBe205() {
        assertEquals(1.45, orderService.submitOrder(listOf("apple", "apple", "orange", "apple")))
    }

    @Test
    fun shouldBe0() {
        assertEquals(0.0, orderService.submitOrder(listOf()))
    }

    @Test
    fun shouldBe0IfItemIsUnknown() {
        assertEquals(0.0, orderService.submitOrder(listOf("carrot")))
    }

    @Test
    fun shouldBe60() {
        assertEquals(0.60, orderService.submitOrder(listOf("apple")))
    }

    @Test
    fun shouldBe25() {
        assertEquals(0.25, orderService.submitOrder(listOf("orange")))
    }
}