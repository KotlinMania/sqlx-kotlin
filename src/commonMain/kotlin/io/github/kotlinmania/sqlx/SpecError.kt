// port-lint: source sqlx/src/spec_error.rs
package io.github.kotlinmania.sqlx

/**
 * Autoderef specialization wrapper for error formatting in SQLx macros.
 */
public class SpecErrorWrapper<E>(
    public val value: E,
) {
    public fun sqlxSpecError(): Throwable =
        when (val v = value) {
            null -> RuntimeException("unprintable error: (unprintable type)")
            is Throwable -> v
            is CharSequence -> RuntimeException(v.toString())
            else -> RuntimeException(v.toString())
        }
}

/**
 * Trait for specializing error conversion for display/debug/exception handling.
 */
public interface SpecError<E> {
    public fun sqlxSpecError(wrapper: SpecErrorWrapper<E>): Throwable
}

/**
 * Converts an arbitrary value into a Throwable.
 */
public fun <E> specError(value: E): Throwable =
    SpecErrorWrapper(value).sqlxSpecError()
