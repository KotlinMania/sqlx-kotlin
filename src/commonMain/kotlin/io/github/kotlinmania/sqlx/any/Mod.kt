// port-lint: source any/mod.rs
package io.github.kotlinmania.sqlx.any

import io.github.kotlinmania.sqlx.AnyDriver
import io.github.kotlinmania.sqlx.installDefaultDrivers as rootInstallDefaultDrivers
import io.github.kotlinmania.sqlx.installDrivers as rootInstallDrivers

// Callers migrated:
// - AnyPool -> io.github.kotlinmania.sqlx.AnyPool
// - AnyConnection -> io.github.kotlinmania.sqlx.AnyConnection

public fun installDefaultDrivers() {
    rootInstallDefaultDrivers()
}

public fun installDrivers(drivers: List<AnyDriver>) {
    rootInstallDrivers(drivers)
}
