// port-lint: source ty_match.rs
package io.github.kotlinmania.sqlx

/**
 * Asserts two values are of the same type at type-check time.
 */
public fun <T> sameType(first: T, second: T) {
    // Compile-time type check
}

/**
 * Allows query macros to match parameter types against expected types.
 */
public class WrapSame<T, U>(
    private val dummy: Any? = null,
) : WrapSameExt<T> {
    public companion object {
        public fun <T, U> new(arg: U): WrapSame<T, U> = WrapSame()
    }

    override fun wrapSame(): Wrapped<T> = Wrapped(conjureValue())
}

/**
 * Wrapper container for wrapped type resolution.
 */
public class Wrapped<T>(
    public val value: T,
)

/**
 * Extension interface for wrapped type resolution.
 */
public interface WrapSameExt<T> {
    public fun wrapSame(): Wrapped<T>
}

/**
 * Type matching resolution for borrowed / wrapped types.
 */
public class MatchBorrow<T, U>(
    public val value: T? = null,
) : MatchBorrowExt<T> {
    public companion object {
        public fun <T, U> new(target: T, arg: U): Pair<T, MatchBorrow<T, U>> =
            Pair(target, MatchBorrow(target))
    }

    override fun matchBorrow(): Matched<T> = Matched(value ?: conjureValue())
}

/**
 * Wrapper container for matched type resolution.
 */
public class Matched<T>(
    public val value: T,
)

/**
 * Extension interface for match-borrow type resolution.
 */
public interface MatchBorrowExt<T> {
    public fun matchBorrow(): Matched<T>
}

/**
 * Placeholder value synthesizer for type resolution in query expansions.
 */
@Suppress("UNCHECKED_CAST")
public fun <T> conjureValue(): T = null as T

/**
 * Duplicate / identity placeholder for type resolution in query expansions.
 */
public fun <T> dupeValue(t: T): T = t
