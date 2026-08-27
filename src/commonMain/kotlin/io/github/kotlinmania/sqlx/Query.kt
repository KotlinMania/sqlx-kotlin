// port-lint: source sqlx/src/lib.rs
package io.github.kotlinmania.sqlx

/**
 * A prepared or dynamic SQL query with arguments.
 */
public class Query<out DB : Database>(
    private val sqlString: String,
    private val args: Arguments<DB> = DefaultArguments(),
) : Execute<DB> {
    override fun sql(): String = sqlString

    override fun arguments(): Arguments<DB>? = args

    public fun bind(value: Any?): Query<DB> {
        args.add(value)
        return this
    }

    public suspend fun execute(executor: Executor<@UnsafeVariance DB>): QueryResult =
        executor.execute(this)

    public suspend fun fetch(executor: Executor<@UnsafeVariance DB>): List<Row<DB>> =
        executor.fetch(this)

    public suspend fun fetchOne(executor: Executor<@UnsafeVariance DB>): Row<DB> =
        executor.fetchOne(this)

    public suspend fun fetchOptional(executor: Executor<@UnsafeVariance DB>): Row<DB>? =
        executor.fetchOptional(this)

    public suspend fun fetchAll(executor: Executor<@UnsafeVariance DB>): List<Row<DB>> =
        executor.fetchAll(this)

    public fun <O> map(transform: (Row<DB>) -> O): MapQuery<DB, O> =
        MapQuery(this, transform)

    public fun <O> tryMap(transform: (Row<DB>) -> O): MapQuery<DB, O> =
        MapQuery(this, transform)
}

/**
 * A query that transforms output rows into instances of [O].
 */
public class MapQuery<out DB : Database, out O>(
    private val query: Query<DB>,
    private val mapper: (Row<DB>) -> O,
) {
    public fun <T> tryMap(transform: (O) -> T): MapQuery<DB, T> =
        MapQuery(query) { row -> transform(mapper(row)) }

    public suspend fun fetch(executor: Executor<@UnsafeVariance DB>): List<O> =
        query.fetch(executor).map(mapper)

    public suspend fun fetchOne(executor: Executor<@UnsafeVariance DB>): O =
        mapper(query.fetchOne(executor))

    public suspend fun fetchOptional(executor: Executor<@UnsafeVariance DB>): O? =
        query.fetchOptional(executor)?.let(mapper)

    public suspend fun fetchAll(executor: Executor<@UnsafeVariance DB>): List<O> =
        fetch(executor)
}

/**
 * A query that deserializes result rows using a [FromRow] implementation.
 */
public class QueryAs<out DB : Database, out O>(
    private val query: Query<DB>,
    private val fromRow: FromRow<Row<DB>, O>,
) {
    public fun bind(value: Any?): QueryAs<DB, O> {
        query.bind(value)
        return this
    }

    public suspend fun fetch(executor: Executor<@UnsafeVariance DB>): List<O> =
        query.fetch(executor).map { fromRow.fromRow(it) }

    public suspend fun fetchOne(executor: Executor<@UnsafeVariance DB>): O =
        fromRow.fromRow(query.fetchOne(executor))

    public suspend fun fetchOptional(executor: Executor<@UnsafeVariance DB>): O? =
        query.fetchOptional(executor)?.let { fromRow.fromRow(it) }

    public suspend fun fetchAll(executor: Executor<@UnsafeVariance DB>): List<O> =
        fetch(executor)
}

/**
 * A query that extracts a single scalar column value from each result row.
 */
public class QueryScalar<out DB : Database, out O>(
    private val query: Query<DB>,
) {
    public fun bind(value: Any?): QueryScalar<DB, O> {
        query.bind(value)
        return this
    }

    public suspend fun fetch(executor: Executor<@UnsafeVariance DB>): List<O> =
        query.fetch(executor).map { it.tryGet<O>(0) }

    public suspend fun fetchOne(executor: Executor<@UnsafeVariance DB>): O =
        query.fetchOne(executor).tryGet<O>(0)

    public suspend fun fetchOptional(executor: Executor<@UnsafeVariance DB>): O? =
        query.fetchOptional(executor)?.tryGet<O>(0)

    public suspend fun fetchAll(executor: Executor<@UnsafeVariance DB>): List<O> =
        fetch(executor)
}

/**
 * Builder for dynamically constructing SQL queries and accumulating bind parameters.
 */
public class QueryBuilder<DB : Database>(
    initialSql: String = "",
) {
    private val sqlBuffer = StringBuilder(initialSql)
    private val args = DefaultArguments<DB>()

    public fun push(sql: String): QueryBuilder<DB> {
        sqlBuffer.append(sql)
        return this
    }

    public fun pushBind(value: Any?): QueryBuilder<DB> {
        args.add(value)
        return this
    }

    public fun sql(): String = sqlBuffer.toString()

    public fun build(): Query<DB> =
        Query(sql(), args)

    public fun <O> buildQueryAs(fromRow: FromRow<Row<DB>, O>): QueryAs<DB, O> =
        QueryAs(build(), fromRow)
}

/**
 * Represents raw unparameterized SQL for direct execution.
 */
public class RawSql<out DB : Database>(
    private val sqlString: String,
) : Execute<DB> {
    override fun sql(): String = sqlString

    override fun arguments(): Arguments<DB>? = null

    public suspend fun execute(executor: Executor<@UnsafeVariance DB>): QueryResult =
        executor.execute(this)

    public suspend fun fetch(executor: Executor<@UnsafeVariance DB>): List<Row<DB>> =
        executor.fetch(this)

    public suspend fun fetchOne(executor: Executor<@UnsafeVariance DB>): Row<DB> =
        executor.fetchOne(this)

    public suspend fun fetchOptional(executor: Executor<@UnsafeVariance DB>): Row<DB>? =
        executor.fetchOptional(this)

    public suspend fun fetchAll(executor: Executor<@UnsafeVariance DB>): List<Row<DB>> =
        executor.fetchAll(this)
}

public fun <DB : Database> query(sql: String): Query<DB> =
    Query(sql, DefaultArguments())

public fun <DB : Database> queryWith(sql: String, arguments: Arguments<DB>): Query<DB> =
    Query(sql, arguments)

public fun <DB : Database, O> queryAs(sql: String, fromRow: FromRow<Row<DB>, O>): QueryAs<DB, O> =
    QueryAs(Query(sql, DefaultArguments()), fromRow)

public fun <DB : Database, O> queryAsWith(
    sql: String,
    fromRow: FromRow<Row<DB>, O>,
    arguments: Arguments<DB>,
): QueryAs<DB, O> = QueryAs(Query(sql, arguments), fromRow)

public fun <DB : Database, O> queryScalar(sql: String): QueryScalar<DB, O> =
    QueryScalar(Query(sql, DefaultArguments()))

public fun <DB : Database, O> queryScalarWith(
    sql: String,
    arguments: Arguments<DB>,
): QueryScalar<DB, O> = QueryScalar(Query(sql, arguments))

public fun <DB : Database> rawSql(sql: String): RawSql<DB> = RawSql(sql)
