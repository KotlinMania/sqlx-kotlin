// port-lint: source sqlx/src/lib.rs
package io.github.kotlinmania.sqlx

/**
 * Collection of bound parameters for a database query.
 */
public interface Arguments<out DB : Database> {
    public fun add(value: Any?)

    public fun values(): List<Any?>

    public fun size(): Int = values().size

    public fun isEmpty(): Boolean = values().isEmpty()
}

/**
 * Trait for types that can be converted into query arguments.
 */
public interface IntoArguments<out DB : Database> {
    public fun intoArguments(): Arguments<DB>
}

/**
 * Default generic arguments container.
 */
public class DefaultArguments<out DB : Database> :
    Arguments<DB>,
    IntoArguments<DB> {
    private val buffer: MutableList<Any?> = mutableListOf()

    override fun add(value: Any?) {
        buffer.add(value)
    }

    override fun values(): List<Any?> = buffer.toList()

    override fun intoArguments(): Arguments<DB> = this
}
