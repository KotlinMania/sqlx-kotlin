// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Indicates whether an encoded value is NULL.
 */
public enum class IsNull {
    No,
    Yes,
}

/**
 * Metadata identifying a database data type.
 */
public interface TypeInfo {
    public val name: String
    public val isNull: Boolean get() = false
}

/**
 * Simple standard type info implementation.
 */
public data class SimpleTypeInfo(
    override val name: String,
    override val isNull: Boolean = false,
) : TypeInfo

/**
 * Maps a Kotlin type to a SQL database type.
 */
public interface SqlType<DB : Database> {
    public fun typeInfo(): TypeInfo
}

/**
 * Encodes a Kotlin value into a database parameter representation.
 */
public interface Encode<DB : Database> {
    public fun encode(value: Any?, arguments: Arguments<DB>): IsNull
}

/**
 * Decodes a database value into a Kotlin instance of [T].
 */
public interface Decode<DB : Database, out T> {
    public fun decode(value: Value<DB>): T
}

/**
 * Represents a single scalar database value.
 */
public interface Value<DB : Database> {
    public fun typeInfo(): TypeInfo

    public fun isNull(): Boolean

    public fun asRaw(): Any?
}

/**
 * Represents a borrowed reference to a scalar database value.
 */
public interface ValueRef<DB : Database> {
    public fun typeInfo(): TypeInfo

    public fun isNull(): Boolean

    public fun asRaw(): Any?
}
