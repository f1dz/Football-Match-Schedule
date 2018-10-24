package `in`.khofid.schedule.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class UtilsKtTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    /**
     * Test to parsing text with semicolon separated, into newline separated
     */
    @Test
    fun normalize() {
        val text = "Text; To; Test"
        val expected = "Text\nTo\nTest"
        val actual = text.normalize()
        assertEquals("String.normalize() test failed", expected, actual)
    }

    /**
     * Test function to convert date to expected format
     */
    @Test
    fun toSimpleDate() {
        val text = "2018-10-24"
        val expected = "Rab, 24 Okt 2018"
        assertEquals("String.toSimpleDate() failed", expected, text.toSimpleDate())
    }

    /**
     * Test UTC time to localtime with short format
     * example: 01:00:00 UTC should be 08:00 (WIB)
     */
    @Test
    fun toLocalTime() {
        val text = "14:00:00+00:00"
        val expected = "21:00"
        assertEquals("String.toLocalTime", expected, text.toLocalTime())
    }

    /**
     * Test UTC date to local date (device/user date)
     * The result will formatted as needed
     * example: 2018-10-24 21:00:00 UTC should be Kam, 25 Okt 2018
     */
    @Test
    fun toLocalDate() {
        val date = "2018-10-24"
        val time = "21:00:00+00:00"
        val expected = "Kam, 25 Okt 2018"
        assertEquals("String.toLocalDate()", expected, date.toLocalDate(time))
    }
}