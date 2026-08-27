// port-lint: source lib.rs
package io.github.kotlinmania.sqlx

/**
 * SQLx library metadata.
 */
public object Sqlx {
    public const val VERSION: String = "0.8.3"
}

// Callers migrated:
// - prelude::Acquire -> io.github.kotlinmania.sqlx.Acquire
// - prelude::ConnectOptions -> io.github.kotlinmania.sqlx.ConnectOptions
// - prelude::Connection -> io.github.kotlinmania.sqlx.Connection
// - prelude::Decode -> io.github.kotlinmania.sqlx.Decode
// - prelude::Encode -> io.github.kotlinmania.sqlx.Encode
// - prelude::Executor -> io.github.kotlinmania.sqlx.Executor
// - prelude::FromRow -> io.github.kotlinmania.sqlx.FromRow
// - prelude::IntoArguments -> io.github.kotlinmania.sqlx.IntoArguments
// - prelude::Row -> io.github.kotlinmania.sqlx.Row
// - prelude::Statement -> io.github.kotlinmania.sqlx.Statement
// - prelude::Type -> io.github.kotlinmania.sqlx.SqlType
