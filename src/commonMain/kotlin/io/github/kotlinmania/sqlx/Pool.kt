// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Configuration options for a connection pool.
 */
public data class PoolOptions<out DB : Database>(
    public val maxConnections: Int = 10,
    public val minConnections: Int = 0,
    public val acquireTimeoutMillis: Long = 30_000L,
    public val idleTimeoutMillis: Long? = 600_000L,
    public val maxLifetimeMillis: Long? = 1_800_000L,
    public val testOnAcquire: Boolean = true,
) {
    public constructor() : this(10, 0, 30_000L, 600_000L, 1_800_000L, true)

    public fun maxConnections(max: Int): PoolOptions<DB> = copy(maxConnections = max)

    public fun minConnections(min: Int): PoolOptions<DB> = copy(minConnections = min)

    public fun acquireTimeoutMillis(timeout: Long): PoolOptions<DB> = copy(acquireTimeoutMillis = timeout)

    public fun testOnAcquire(test: Boolean): PoolOptions<DB> = copy(testOnAcquire = test)
}

/**
 * An asynchronous connection pool for managing database connections.
 */
public interface Pool<out DB : Database> :
    Executor<DB>,
    Acquire<DB> {
    public fun isClosed(): Boolean

    public fun size(): Int

    public fun numIdle(): Int

    public fun options(): PoolOptions<Database>

    public suspend fun close()
}
