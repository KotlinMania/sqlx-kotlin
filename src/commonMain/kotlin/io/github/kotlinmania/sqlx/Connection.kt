// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Options used to establish a new database connection.
 */
public interface ConnectOptions<DB : Database> {
    public suspend fun connect(): Connection<DB>
}

/**
 * Represents a single live connection to a database.
 */
public interface Connection<DB : Database> : Executor<DB> {
    public suspend fun close()

    public suspend fun ping()

    public suspend fun beginTransaction(): Transaction<DB>
}

/**
 * Manages transaction lifecycles for a database connection.
 */
public interface TransactionManager<DB : Database> {
    public suspend fun begin(connection: Connection<DB>)

    public suspend fun commit(connection: Connection<DB>)

    public suspend fun rollback(connection: Connection<DB>)

    public suspend fun startRollback(connection: Connection<DB>)
}

/**
 * An in-flight database transaction wrapper around a [Connection].
 */
public interface Transaction<DB : Database> : Executor<DB> {
    public suspend fun commit()

    public suspend fun rollback()
}

/**
 * A prepared or cached SQL statement.
 */
public interface Statement<DB : Database> {
    public fun sql(): String

    public fun columns(): List<Column<DB>>
}

/**
 * Schema and nullability description of a prepared SQL query.
 */
public data class Describe<DB : Database>(
    public val columns: List<Column<DB>>,
    public val nullable: List<Boolean?> = emptyList(),
)
