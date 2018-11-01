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

    @Test
    fun toLocalDateTime() {
        val date = "2018-11-01"
        val time = "23:00:00+00:00:00"
        val expected = "2018-11-02 06:00:00"
        assertEquals(expected, date.toLocalDateTime(time))
    }

    @Test
    fun toMillis() {
        val dateTime = "2018-10-31 12:15:00"
        val expected = 1540962900000L
        assertEquals(expected, dateTime.toMillis())
    }

    @Test
    fun isPast(){
        val dateTime = "2018-10-31 12:15:00"
        val expected = true
        assertEquals(expected, dateTime.isPast())
    }

    @Test
    fun encodeUrl(){
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=UEFA Nations League"
        val expected = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=UEFA%20Nations%20League"
        assertEquals(expected, url.encodeUrl())
    }
}