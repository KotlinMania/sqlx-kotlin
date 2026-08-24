// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Disjoint union of two types [L] and [R].
 */
public sealed class Either<out L, out R> {
    public data class Left<out L>(
        public val value: L,
    ) : Either<L, Nothing>()

    public data class Right<out R>(
        public val value: R,
    ) : Either<Nothing, R>()

    public val isLeft: Boolean get() = this is Left
    public val isRight: Boolean get() = this is Right

    public fun left(): L? =
        when (this) {
            is Left -> value
            is Right -> null
        }

    public fun right(): R? =
        when (this) {
            is Left -> null
            is Right -> value
        }

    public inline fun <T> fold(onLeft: (L) -> T, onRight: (R) -> T): T =
        when (this) {
            is Left -> onLeft(value)
            is Right -> onRight(value)
        }

    public inline fun <T> mapLeft(transform: (L) -> T): Either<T, R> =
        when (this) {
            is Left -> Left(transform(value))
            is Right -> this
        }

    public inline fun <T> mapRight(transform: (R) -> T): Either<L, T> =
        when (this) {
            is Left -> this
            is Right -> Right(transform(value))
        }
}
