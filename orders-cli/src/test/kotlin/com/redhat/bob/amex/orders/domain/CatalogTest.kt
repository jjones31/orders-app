package com.redhat.bob.amex.orders.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertFailsWith

internal class CatalogTest {

    var catalog: Catalog = Catalog()

    @BeforeEach
    fun beforeEach() {
        catalog = Catalog()
    }

    @Test
    fun findByName() {
        assertNotNull(catalog.findByName("orange"))
        assertNotNull(catalog.findByName("apple"))
        assertNotNull(catalog.findByName("APPLE"))
    }

    @Test
    fun shouldThrowNoSuchElementIfNotFound() {
        val catalog = Catalog()
        assertFailsWith<NoSuchElementException> { catalog.findByName("banana") }
    }

    @Test
    fun shouldReduceStockByAmount() {
        assertEquals(10, catalog.findByName("apple").quantity)
        catalog.reduceStock("APPLE", 5)
        assertEquals(5, catalog.findByName("apple").quantity)
    }

    @Test
    fun shouldReduceShouldNotGoBelowZero() {
        assertEquals(10, catalog.findByName("apple").quantity)
        catalog.reduceStock("APPLE", 50)
        assertEquals(0, catalog.findByName("apple").quantity)
    }
}