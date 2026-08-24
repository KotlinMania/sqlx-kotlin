// port-lint: source any/mod.rs
package io.github.kotlinmania.sqlx.any

import io.github.kotlinmania.sqlx.AnyConnection
import io.github.kotlinmania.sqlx.AnyDriver
import io.github.kotlinmania.sqlx.AnyPool
import io.github.kotlinmania.sqlx.installDefaultDrivers as rootInstallDefaultDrivers
import io.github.kotlinmania.sqlx.installDrivers as rootInstallDrivers

public typealias AnyPool = AnyPool
public typealias AnyConnection = AnyConnection

public fun installDefaultDrivers() {
    rootInstallDefaultDrivers()
}

public fun installDrivers(drivers: List<AnyDriver>) {
    rootInstallDrivers(drivers)
}
