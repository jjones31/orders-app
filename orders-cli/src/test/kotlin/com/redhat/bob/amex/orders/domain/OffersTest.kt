package com.redhat.bob.amex.orders.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OffersTest {

    @Test
    fun shouldOffer60forThreeApples() {
        val offers = Offers()
        assertEquals(60, offers.handleDiscounts(Order(mutableListOf(Catalog.APPLE, Catalog.APPLE,
            Catalog.APPLE))))
    }

    @Test
    fun shouldOffer0forOneApples() {
        val offers = Offers()
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(Catalog.APPLE))))
    }

    @Test
    fun shouldOffer0forNoApples() {
        val offers = Offers()
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(Catalog.ORANGE))))
    }

    @Test
    fun shouldOffer25forThreeOranges() {
        val offers = Offers()
        assertEquals(25, offers.handleDiscounts(Order(mutableListOf(Catalog.ORANGE, Catalog.ORANGE,
            Catalog.ORANGE))))
    }

    @Test
    fun shouldOffer0forOneOrange() {
        val offers = Offers()
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(Catalog.ORANGE))))
    }

    @Test
    fun shouldOffer0forTwoOrange() {
        val offers = Offers()
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(Catalog.ORANGE, Catalog.ORANGE))))
    }

    @Test
    fun shouldOffer0forOneAppleTwoOrange() {
        val offers = Offers()
        assertEquals(0, offers.handleDiscounts(Order(mutableListOf(Catalog.ORANGE, Catalog.ORANGE, Catalog.APPLE))))
    }
}