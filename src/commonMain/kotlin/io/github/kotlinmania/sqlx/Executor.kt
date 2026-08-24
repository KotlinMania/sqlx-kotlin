// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * An executable SQL statement with optional bound arguments.
 */
public interface Execute<DB : Database> {
    public fun sql(): String

    public fun arguments(): Arguments<DB>? = null
}

/**
 * Simple container for an executable query with optional arguments.
 */
public data class SimpleExecute<DB : Database>(
    private val sqlString: String,
    private val args: Arguments<DB>? = null,
) : Execute<DB> {
    override fun sql(): String = sqlString

    override fun arguments(): Arguments<DB>? = args
}

/**
 * Interface for database targets that can execute queries (Connection, Transaction, Pool).
 */
public interface Executor<DB : Database> {
    public suspend fun execute(sql: String): QueryResult =
        execute(SimpleExecute(sql))

    public suspend fun execute(query: Execute<DB>): QueryResult

    public suspend fun fetch(sql: String): List<Row<DB>> =
        fetch(SimpleExecute(sql))

    public suspend fun fetch(query: Execute<DB>): List<Row<DB>>

    public suspend fun fetchOne(sql: String): Row<DB> =
        fetchOne(SimpleExecute(sql))

    public suspend fun fetchOne(query: Execute<DB>): Row<DB> {
        val rows = fetch(query)
        if (rows.isEmpty()) {
            throw SqlxException.RowNotFound()
        }
        return rows.first()
    }

    public suspend fun fetchOptional(sql: String): Row<DB>? =
        fetchOptional(SimpleExecute(sql))

    public suspend fun fetchOptional(query: Execute<DB>): Row<DB>? {
        val rows = fetch(query)
        return rows.firstOrNull()
    }

    public suspend fun fetchAll(sql: String): List<Row<DB>> =
        fetchAll(SimpleExecute(sql))

    public suspend fun fetchAll(query: Execute<DB>): List<Row<DB>> =
        fetch(query)
}

/**
 * Trait for obtaining a database connection (e.g. from a pool or directly).
 */
public interface Acquire<DB : Database> {
    public suspend fun acquire(): Connection<DB>
}
