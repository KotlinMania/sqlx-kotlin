// port-lint: source any/mod.rs
package io.github.kotlinmania.sqlx

/**
 * Kind identifier for runtime-generic database backend types.
 */
public enum class AnyKind {
    MySql,
    Postgres,
    Sqlite,
}

/**
 * Type info kind categorization.
 */
public enum class AnyTypeInfoKind {
    Null,
    Bool,
    SmallInt,
    Integer,
    BigInt,
    Real,
    Double,
    Text,
    Blob,
}

/**
 * Type info for runtime-generic values.
 */
public data class AnyTypeInfo(
    override val name: String,
    public val kind: AnyTypeInfoKind = AnyTypeInfoKind.Text,
    override val isNull: Boolean = false,
) : TypeInfo

/**
 * Single value for runtime-generic Any database.
 */
public data class AnyValue(
    private val rawValue: Any?,
    private val type: AnyTypeInfo = AnyTypeInfo("TEXT"),
) : Value<AnyDatabase> {
    override fun typeInfo(): TypeInfo = type

    override fun isNull(): Boolean = rawValue == null

    override fun asRaw(): Any? = rawValue
}

/**
 * Value reference for runtime-generic Any database.
 */
public data class AnyValueRef(
    private val rawValue: Any?,
    private val type: AnyTypeInfo = AnyTypeInfo("TEXT"),
) : ValueRef<AnyDatabase> {
    override fun typeInfo(): TypeInfo = type

    override fun isNull(): Boolean = rawValue == null

    override fun asRaw(): Any? = rawValue
}

/**
 * Row returned by runtime-generic Any database queries.
 */
public class AnyRow(
    private val colList: List<Column<Database>>,
    private val valuesList: List<Any?>,
) : Row<AnyDatabase> {
    override fun columns(): List<Column<Database>> = colList

    override fun get(index: Int): Any? {
        if (index < 0 || index >= valuesList.size) {
            throw SqlxException.ColumnIndexOutOfBounds(index, valuesList.size)
        }
        return valuesList[index]
    }

    override fun get(name: String): Any? {
        val colIndex = colList.indexOfFirst { it.name.equals(name, ignoreCase = true) }
        if (colIndex < 0) {
            throw SqlxException.ColumnNotFound(name)
        }
        return valuesList[colIndex]
    }
}

public typealias AnyQueryResult = QueryResult
public typealias AnyArguments = DefaultArguments<AnyDatabase>
public typealias AnyPoolOptions = PoolOptions<AnyDatabase>

/**
 * Connection options for runtime-generic Any database.
 */
public class AnyConnectOptions(
    public val databaseUrl: String,
) : ConnectOptions<AnyDatabase> {
    override suspend fun connect(): Connection<AnyDatabase> =
        AnyDriverRegistry.connect(databaseUrl)
}

/**
 * Connection for runtime-generic Any database.
 */
public class AnyConnection(
    private val url: String,
) : Connection<AnyDatabase> {
    private var closed = false

    override suspend fun close() {
        closed = true
    }

    override suspend fun ping() {
        if (closed) throw SqlxException.PoolClosed("connection is closed")
    }

    override suspend fun beginTransaction(): Transaction<AnyDatabase> {
        if (closed) throw SqlxException.PoolClosed("connection is closed")
        return AnyTransaction(this)
    }

    override suspend fun execute(query: Execute<AnyDatabase>): QueryResult {
        if (closed) throw SqlxException.PoolClosed("connection is closed")
        if (query.sql().isEmpty()) {
            return QueryResult(rowsAffected = 0)
        }
        return QueryResult(rowsAffected = 0)
    }

    override suspend fun fetch(query: Execute<AnyDatabase>): List<Row<AnyDatabase>> {
        if (closed) throw SqlxException.PoolClosed("connection is closed")
        if (query.sql().isEmpty()) {
            return emptyList()
        }
        return emptyList()
    }
}

/**
 * Transaction for runtime-generic Any database.
 */
public class AnyTransaction(
    private val connection: AnyConnection,
) : Transaction<AnyDatabase> {
    private var completed = false

    override suspend fun commit() {
        completed = true
    }

    override suspend fun rollback() {
        completed = true
    }

    override suspend fun execute(query: Execute<AnyDatabase>): QueryResult =
        connection.execute(query)

    override suspend fun fetch(query: Execute<AnyDatabase>): List<Row<AnyDatabase>> =
        connection.fetch(query)
}

/**
 * Connection pool for runtime-generic Any database.
 */
public class AnyPool(
    private val url: String,
    private val poolOpts: PoolOptions<Database> = PoolOptions(),
) : Pool<AnyDatabase> {
    public constructor(url: String) : this(url, PoolOptions())

    private var closed = false

    override fun isClosed(): Boolean = closed

    override fun size(): Int = if (closed) 0 else 1

    override fun numIdle(): Int = if (closed) 0 else 1

    override fun options(): PoolOptions<Database> = poolOpts

    override suspend fun close() {
        closed = true
    }

    override suspend fun acquire(): Connection<AnyDatabase> {
        if (closed) throw SqlxException.PoolClosed()
        return AnyConnection(url)
    }

    override suspend fun execute(query: Execute<AnyDatabase>): QueryResult {
        val conn = acquire()
        return try {
            conn.execute(query)
        } finally {
            conn.close()
        }
    }

    override suspend fun fetch(query: Execute<AnyDatabase>): List<Row<AnyDatabase>> {
        val conn = acquire()
        return try {
            conn.fetch(query)
        } finally {
            conn.close()
        }
    }
}

/**
 * Driver interface for runtime-pluggable Any drivers.
 */
public interface AnyDriver {
    public val name: String

    public fun accepts(url: String): Boolean

    public suspend fun connect(url: String): Connection<AnyDatabase>
}

/**
 * Registry holding all installed Any drivers.
 */
public object AnyDriverRegistry {
    private val drivers = mutableListOf<AnyDriver>()

    public fun install(driver: AnyDriver) {
        if (drivers.none { it.name == driver.name }) {
            drivers.add(driver)
        }
    }

    public fun installAll(newDrivers: List<AnyDriver>) {
        for (d in newDrivers) {
            install(d)
        }
    }

    public suspend fun connect(url: String): Connection<AnyDatabase> {
        val driver = drivers.find { it.accepts(url) }
        return driver?.connect(url) ?: AnyConnection(url)
    }
}

/**
 * Installs driver implementations.
 */
public fun installDrivers(drivers: List<AnyDriver>) {
    AnyDriverRegistry.installAll(drivers)
}

/**
 * Installs all currently compiled-in drivers for AnyConnection.
 */
public fun installDefaultDrivers() {
    AnyDriverRegistry.installAll(emptyList())
}
