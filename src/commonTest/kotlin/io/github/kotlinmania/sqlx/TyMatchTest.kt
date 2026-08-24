// port-lint: tests ty_match.rs
package io.github.kotlinmania.sqlx

import kotlin.test.Test
import kotlin.test.assertEquals

class TyMatchTest {
    @Test
    fun testDupeValue() {
        val i: Int = dupeValue(0)
        assertEquals(0, i)

        val s: String = dupeValue("test")
        assertEquals("test", s)
    }

    @Test
    fun testWrapSame() {
        val wrapped = WrapSame.new<Int, Int>(0)
        sameType(wrapped, WrapSame.new<Int, Int>(1))
    }

    @Test
    fun testMatchBorrow() {
        val (strVal, matchBorrowStr) = MatchBorrow.new<String, String>("", "hello")
        assertEquals("", strVal)
        assertEquals("", matchBorrowStr.matchBorrow().value)

        val (intVal, matchBorrowInt) = MatchBorrow.new<Long, Long>(0L, 0L)
        assertEquals(0L, intVal)
        assertEquals(0L, matchBorrowInt.matchBorrow().value)
    }
}
