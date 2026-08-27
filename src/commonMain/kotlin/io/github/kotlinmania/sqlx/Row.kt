// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Metadata for a single column in a query result set.
 */
public interface Column<out DB : Database> {
    public val ordinal: Int
    public val name: String
    public val typeInfo: TypeInfo
}

/**
 * Basic column implementation.
 */
public data class DefaultColumn<out DB : Database>(
    override val ordinal: Int,
    override val name: String,
    override val typeInfo: TypeInfo = SimpleTypeInfo("TEXT"),
) : Column<DB>

/**
 * Resolves a column identifier (ordinal index or name) to an index within a row.
 */
public interface ColumnIndex<R> {
    public fun index(row: R): Int
}

/**
 * Represents a single row returned from a SQL query execution.
 */
public interface Row<out DB : Database> {
    public fun columns(): List<Column<Database>>

    public fun columnCount(): Int = columns().size

    public fun column(index: Int): Column<Database> {
        val cols = columns()
        if (index < 0 || index >= cols.size) {
            throw SqlxException.ColumnIndexOutOfBounds(index, cols.size)
        }
        return cols[index]
    }

    public fun column(name: String): Column<Database> {
        val cols = columns()
        return cols.find { it.name.equals(name, ignoreCase = true) }
            ?: throw SqlxException.ColumnNotFound(name)
    }

    public fun get(index: Int): Any?

    public fun get(name: String): Any?

    public fun <T> tryGet(index: Int): T {
        val raw = get(index)
        @Suppress("UNCHECKED_CAST")
        return raw as T
    }

    public fun <T> tryGet(name: String): T {
        val raw = get(name)
        @Suppress("UNCHECKED_CAST")
        return raw as T
    }
}

/**
 * Trait for types that can be constructed from a database row.
 */
public fun interface FromRow<in R, out T> {
    public fun fromRow(row: R): T
}
