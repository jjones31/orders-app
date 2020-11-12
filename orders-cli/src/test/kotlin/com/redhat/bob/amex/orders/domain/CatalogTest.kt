package com.redhat.bob.amex.orders.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFailsWith

internal class CatalogTest {

    val catalog = Catalog()

    @Test
    fun findByName() {
        assertNotNull(catalog.findByName("orange"))
        assertNotNull(catalog.findByName("apple"))
        assertNotNull(catalog.findByName("APPLE"))
    }

    @Test
    fun shouldThrowNoSuchElementIfNotFound() {
        assertFailsWith<NoSuchElementException> { catalog.findByName("banana") }
    }
}