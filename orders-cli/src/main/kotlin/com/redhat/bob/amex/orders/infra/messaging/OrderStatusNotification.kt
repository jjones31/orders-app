package com.redhat.bob.amex.orders.infra.messaging

import java.time.LocalDate

data class OrderStatusNotification(
 val shippingDate: LocalDate,
 val status: String,
 val total: Double,
 val totalDiscounts: Double,
 val discountedTotal: Double)