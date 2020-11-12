package com.redhat.bob.amex.orders

import com.redhat.bob.amex.orders.infra.messaging.OrderStatusNotification
import com.redhat.bob.amex.orders.service.NotificationService
import com.redhat.bob.amex.orders.service.OrderService
import java.util.*

/**
 * The OrdersConsole encapsulates logic for calling the OrderService given the input.
 * Note the inputProcessor function and outputProcessor function are used as a strategy
 * for being able to control how IO is processed.
 */
class OrdersConsole(private val orderService: OrderService,
                    val inputProcessor: () -> List<String>?,
                    val outputProcessor: (output: String) -> Unit
) : Observer {

    // Run the console in an infinite loop by default.
    fun run(isForever: Boolean = true) {
        do {
            // We should place at least one order.
            orderService.submitOrder(inputProcessor())
        } while (isForever)
    }

    override fun update(o: Observable?, arg: Any?) {
        when(o) {
            is NotificationService -> {
                if (arg is OrderStatusNotification) {
                    outputProcessor("Your order will be shipped on ${arg.shippingDate}.\n" +
                            "Price: \$${arg.total}\n" +
                            "Discounts: \$${arg.totalDiscounts}\n" +
                            "TOTAL: \$${arg.discountedTotal}\n")
                }
            }
        }
    }
}

/**
 * Orders CLI entrypoint.
 */
fun main() {

    // Wire up the various services.
    val notificationService = NotificationService()
    val orderService = OrderService(notificationService = notificationService)

    // Create a console with stdin and stdout as the input and output processor.
    val console = OrdersConsole(orderService,
        inputProcessor = { readLine()?.split(',') },
        outputProcessor = { println(it) })

    // Add the console as an observer of the notification service.
    notificationService.addObserver(console)

    // start the console forever!
    console.run()
}
