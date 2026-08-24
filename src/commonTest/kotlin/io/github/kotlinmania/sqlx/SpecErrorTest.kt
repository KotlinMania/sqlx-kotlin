// port-lint: tests spec_error.rs
package io.github.kotlinmania.sqlx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SpecErrorTest {
    @Test
    fun testSpecError() {
        val err1 =
            SpecErrorWrapper(RuntimeException("unsupported")).sqlxSpecError()(
                SpecErrorWrapper(RuntimeException("unsupported")),
            )
        assertNotNull(err1)

        val err2 =
            SpecErrorWrapper("displayable error").sqlxSpecError()(
                SpecErrorWrapper("displayable error"),
            )
        assertEquals("displayable error", err2.message)

        val err3 =
            SpecErrorWrapper(DebugError()).sqlxSpecError()(
                SpecErrorWrapper(DebugError()),
            )
        assertEquals("DebugError", err3.message)

        val err4 =
            SpecErrorWrapper(AnyError()).sqlxSpecError()(
                SpecErrorWrapper(AnyError()),
            )
        assertNotNull(err4)

        val err5 =
            SpecErrorWrapper(1).sqlxSpecError()(
                SpecErrorWrapper(1),
            )
        assertEquals("1", err5.message)
    }
}
