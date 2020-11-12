package com.redhat.bob.amex.orders.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OffersTest {

    private var catalog = Catalog()

    @Test
    fun shouldOffer60forThreeApples() {
        val offers = Offers(catalog)
        assertEquals(60, offers.handleDiscounts(Order(mutableListOf(catalog.APPLE, catalog.APPLE,
            catalog.APPLE))))
    }

    @Test
    fun shouldOffer0forOneApples() {
        val offers = Offers(catalog)
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(catalog.APPLE))))
    }

    @Test
    fun shouldOffer0forNoApples() {
        val offers = Offers(catalog)
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(catalog.ORANGE))))
    }

    @Test
    fun shouldOffer25forThreeOranges() {
        val offers = Offers(catalog)
        assertEquals(25, offers.handleDiscounts(Order(mutableListOf(catalog.ORANGE, catalog.ORANGE,
            catalog.ORANGE))))
    }

    @Test
    fun shouldOffer0forOneOrange() {
        val offers = Offers(catalog)
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(catalog.ORANGE))))
    }

    @Test
    fun shouldOffer0forTwoOrange() {
        val offers = Offers(catalog)
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(catalog.ORANGE, catalog.ORANGE))))
    }

    @Test
    fun shouldOffer0forOneAppleTwoOrange() {
        val offers = Offers(catalog)
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(catalog.ORANGE, catalog.ORANGE, catalog.APPLE))))
    }
}