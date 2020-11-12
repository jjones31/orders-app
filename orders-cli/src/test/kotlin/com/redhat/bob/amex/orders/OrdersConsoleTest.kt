package com.redhat.bob.amex.orders

import com.redhat.bob.amex.orders.service.NotificationService
import com.redhat.bob.amex.orders.service.OrderService
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class OrdersConsoleTest {

    @Test
    fun shouldGive85TotalforOrangeAndApple() {
        var output = ""

        val notificationService = NotificationService()
        val orderService = OrderService(notificationService = notificationService)

        val console = OrdersConsole(orderService = orderService,
            inputProcessor = { mutableListOf("orange", "apple") },
            outputProcessor = { output = it })

        notificationService.addObserver(console)

        console.run(false)

        assertTrue(output.contains("TOTAL: \$0.85"))
    }

    @Test
    fun shouldGive85TotalWithCrazySpaces() {
        var output = ""

        val notificationService = NotificationService()
        val orderService = OrderService(notificationService = notificationService)

        val console = OrdersConsole(orderService,
            inputProcessor = { mutableListOf("orange     ", "   apPle") },
            outputProcessor = { output = it })

        notificationService.addObserver(console)

        console.run(false)

        assertTrue(output.contains("TOTAL: \$0.85"))
    }

    @Test
    fun shouldGiveNoOutputforNullList() {
        var output = ""

        val notificationService = NotificationService()
        val orderService = OrderService(notificationService = notificationService)

        val console = OrdersConsole(orderService,
            inputProcessor = { null },
            outputProcessor = { output = it })

        notificationService.addObserver(console)

        console.run(false)

        assertTrue(output.contains("TOTAL: \$0.0"))
    }
}