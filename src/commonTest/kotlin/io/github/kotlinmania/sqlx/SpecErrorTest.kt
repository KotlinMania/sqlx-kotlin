// port-lint: tests spec_error.rs
package io.github.kotlinmania.sqlx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SpecErrorTest {
    private class DebugError {
        override fun toString(): String = "DebugError"
    }

    private class AnyError

    @Test
    fun testSpecError() {
        val err1 = SpecErrorWrapper(RuntimeException("unsupported")).sqlxSpecError()
        assertNotNull(err1)

        val err2 = SpecErrorWrapper("displayable error").sqlxSpecError()
        assertEquals("displayable error", err2.message)

        val err3 = SpecErrorWrapper(DebugError()).sqlxSpecError()
        assertEquals("DebugError", err3.message)

        val err4 = SpecErrorWrapper(AnyError()).sqlxSpecError()
        assertNotNull(err4)

        val err5 = SpecErrorWrapper(1).sqlxSpecError()
        assertEquals("1", err5.message)

        val err6 = specError("direct helper")
        assertEquals("direct helper", err6.message)
    }
}

