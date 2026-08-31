// port-lint: source sqlx/src/lib.rs
package io.github.kotlinmania.sqlx

/**
 * Base exception for all SQLx operations.
 */
public open class SqlxException(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    /**
     * Error when configuration is invalid or missing.
     */
    public class Configuration(
        message: String,
    ) : SqlxException(message)

    /**
     * Database-level error reported by the server or engine.
     */
    public class Database(
        message: String,
        public val code: String? = null,
        cause: Throwable? = null,
    ) : SqlxException(message, cause)

    /**
     * I/O error during network or file operations.
     */
    public class Io(
        message: String,
        cause: Throwable? = null,
    ) : SqlxException(message, cause)

    /**
     * Protocol-level framing or serialization error.
     */
    public class ProtocolError(
        message: String,
    ) : SqlxException(message)

    /**
     * Error returned when expecting at least one row but none were found.
     */
    public class RowNotFound(
        message: String = "no rows returned by a query that expected at least one",
    ) : SqlxException(message)

    /**
     * Error returned when a requested column is not found in the row.
     */
    public class ColumnNotFound(
        message: String,
    ) : SqlxException(message)

    /**
     * Error returned when a column index is out of bounds.
     */
    public class ColumnIndexOutOfBounds(
        public val index: Int,
        public val size: Int,
    ) : SqlxException("column index $index out of bounds for row of size $size")

    /**
     * Error returned when decoding a column value into a target type fails.
     */
    public class Decode(
        message: String,
        cause: Throwable? = null,
    ) : SqlxException(message, cause)

    /**
     * Error returned when type information is not found.
     */
    public class TypeNotFound(
        public val typeName: String,
    ) : SqlxException("type not found: $typeName")

    /**
     * Error returned when acquiring a connection from the pool times out.
     */
    public class PoolTimedOut(
        message: String = "timed out waiting for an open connection from the pool",
    ) : SqlxException(message)

    /**
     * Error returned when attempting to use a closed connection pool.
     */
    public class PoolClosed(
        message: String = "attempted to acquire a connection from a closed pool",
    ) : SqlxException(message)

    /**
     * Error returned during migration execution.
     */
    public class Migrate(
        message: String,
        cause: Throwable? = null,
    ) : SqlxException(message, cause)
}
