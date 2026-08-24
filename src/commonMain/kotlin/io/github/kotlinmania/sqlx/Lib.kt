// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * Top-level convenience re-exports and prelude module.
 */
public object Prelude {
    public typealias Acquire<DB> = io.github.kotlinmania.sqlx.Acquire<DB>
    public typealias ConnectOptions<DB> = io.github.kotlinmania.sqlx.ConnectOptions<DB>
    public typealias Connection<DB> = io.github.kotlinmania.sqlx.Connection<DB>
    public typealias Decode<DB, T> = io.github.kotlinmania.sqlx.Decode<DB, T>
    public typealias Encode<DB> = io.github.kotlinmania.sqlx.Encode<DB>
    public typealias Executor<DB> = io.github.kotlinmania.sqlx.Executor<DB>
    public typealias FromRow<R, T> = io.github.kotlinmania.sqlx.FromRow<R, T>
    public typealias IntoArguments<DB> = io.github.kotlinmania.sqlx.IntoArguments<DB>
    public typealias Row<DB> = io.github.kotlinmania.sqlx.Row<DB>
    public typealias Statement<DB> = io.github.kotlinmania.sqlx.Statement<DB>
    public typealias SqlType<DB> = io.github.kotlinmania.sqlx.SqlType<DB>
}
