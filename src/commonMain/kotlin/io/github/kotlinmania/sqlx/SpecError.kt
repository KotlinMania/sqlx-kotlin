// port-lint: source spec_error.rs
package io.github.kotlinmania.sqlx

/**
 * Autoderef specialization wrapper for error formatting in SQLx macros.
 */
public class SpecErrorWrapper<E>(
    public val value: E,
) {
    public fun sqlxSpecError(): (SpecErrorWrapper<E>) -> Throwable = { wrapper ->
        when (val v = wrapper.value) {
            null -> RuntimeException("unprintable error: (unprintable type)")
            is Throwable -> v
            is CharSequence -> RuntimeException(v.toString())
            else -> RuntimeException(v.toString())
        }
    }
}

/**
 * Trait for specializing error conversion for display/debug/exception handling.
 */
public interface SpecError<E> {
    public fun sqlxSpecError(): (SpecErrorWrapper<E>) -> Throwable
}

/**
 * Test structure representing a debuggable error.
 */
public class DebugError(
    override val message: String = "DebugError",
) : Throwable(message)

/**
 * Test structure representing an arbitrary error type.
 */
public class AnyError
