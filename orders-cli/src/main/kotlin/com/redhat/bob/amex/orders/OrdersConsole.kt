package com.redhat.bob.amex.orders

import com.redhat.bob.amex.orders.service.OrderService

/**
 * The OrdersConsole encapsulates logic for calling the OrderService given the input.
 * Note the inputProcessor function and outputProcessor function are used as a strategy
 * for being able to control how IO is processed.
 */
class OrdersConsole(private val orderService: OrderService = OrderService(),
                    val inputProcessor: () -> List<String>?,
                    val outputProcessor: (output: String) -> Unit
) {

    // Run the console in an infinite loop by default.
    fun run(isForever: Boolean = true) {
        do {
            // We should place at least one order.
            outputProcessor("Total: \$${orderService.submitOrder(inputProcessor())}")
        } while (isForever)
    }
}

/**
 * Orders CLI entrypoint.
 */
fun main() {

    // Create a console with stdin and stdout as the input and output processor.
    val console = OrdersConsole(inputProcessor = { readLine()?.split(',') },
        outputProcessor = { println(it) })

    // start the console forever!
    console.run()
}
