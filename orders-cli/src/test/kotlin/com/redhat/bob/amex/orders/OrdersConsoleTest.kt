package com.redhat.bob.amex.orders

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class OrdersConsoleTest {

    @Test
    fun shouldGive85TotalforOrangeAndApple() {
        var output = ""
        val console = OrdersConsole(inputProcessor = { mutableListOf("orange", "apple") },
            outputProcessor = { output = it })

        console.run(false)

        assertEquals("Total: \$0.85", output)
    }

    @Test
    fun shouldGive85TotalWithCrazySpaces() {
        var output = ""
        val console = OrdersConsole(inputProcessor = { mutableListOf("orange     ", "   apPle") },
            outputProcessor = { output = it })

        console.run(false)

        assertEquals("Total: \$0.85", output)
    }

    @Test
    fun shouldGiveNoOutputforNullList() {
        var output = ""
        val console = OrdersConsole(inputProcessor = { null },
            outputProcessor = { output = it })

        console.run(false)

        assertEquals("Total: \$0.0", output)
    }
}