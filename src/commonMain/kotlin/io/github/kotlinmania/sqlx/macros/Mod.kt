// port-lint: source macros/mod.rs
package io.github.kotlinmania.sqlx.macros

import io.github.kotlinmania.sqlx.Arguments
import io.github.kotlinmania.sqlx.Database
import io.github.kotlinmania.sqlx.DefaultArguments
import io.github.kotlinmania.sqlx.FromRow
import io.github.kotlinmania.sqlx.Query
import io.github.kotlinmania.sqlx.QueryAs
import io.github.kotlinmania.sqlx.QueryScalar
import io.github.kotlinmania.sqlx.Row

/**
 * Expands a statically checked SQL query with arguments.
 */
public fun <DB : Database> expandQuery(
    query: String,
    args: Arguments<DB> = DefaultArguments(),
): Query<DB, Arguments<DB>> = Query(query, args)

/**
 * Expands a typed query mapped to a custom record struct.
 */
public fun <DB : Database, O> expandQueryAs(
    query: String,
    fromRow: FromRow<Row<DB>, O>,
    args: Arguments<DB> = DefaultArguments(),
): QueryAs<DB, O, Arguments<DB>> = QueryAs(Query(query, args), fromRow)

/**
 * Expands a scalar query returning a single column.
 */
public fun <DB : Database, O> expandQueryScalar(
    query: String,
    args: Arguments<DB> = DefaultArguments(),
): QueryScalar<DB, O, Arguments<DB>> = QueryScalar(Query(query, args))
