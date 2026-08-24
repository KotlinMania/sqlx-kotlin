// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Marker and metadata interface representing a database backend.
 */
public interface Database {
    public val name: String
}

/**
 * SQLite database backend marker.
 */
public object Sqlite : Database {
    override val name: String get() = "SQLite"
}

/**
 * MySQL database backend marker.
 */
public object MySql : Database {
    override val name: String get() = "MySQL"
}

/**
 * PostgreSQL database backend marker.
 */
public object Postgres : Database {
    override val name: String get() = "PostgreSQL"
}

/**
 * Runtime-generic database backend marker.
 */
public object AnyDatabase : Database {
    override val name: String get() = "Any"
}

/**
 * Result of an executed non-query SQL statement (e.g. INSERT, UPDATE, DELETE).
 */
public data class QueryResult(
    public val rowsAffected: Long,
    public val lastInsertId: Long? = null,
)
