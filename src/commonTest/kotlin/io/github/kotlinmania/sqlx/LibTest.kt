// port-lint: tests lib.rs
package io.github.kotlinmania.sqlx

import kotlin.test.Test
import kotlin.test.assertEquals

class LibTest {
    @Test
    fun testSqlxVersion() {
        assertEquals("0.8.3", Sqlx.VERSION)
    }
}
