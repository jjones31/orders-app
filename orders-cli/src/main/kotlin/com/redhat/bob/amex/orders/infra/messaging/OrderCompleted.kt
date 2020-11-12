package com.redhat.bob.amex.orders.infra.messaging

import com.redhat.bob.amex.orders.domain.Order

data class OrderCompleted(
    var order: Order,
    var total: Double,
    var discountedTotal: Double,
    var totalDiscount: Double
)