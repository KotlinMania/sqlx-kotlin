// port-lint: tests ../tests/any/any.rs
package io.github.kotlinmania.sqlx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AnyTest {
    @Test
    fun testAnyKindValues() {
        val kinds = AnyKind.entries
        assertEquals(3, kinds.size)
        assertTrue(kinds.contains(AnyKind.MySql))
        assertTrue(kinds.contains(AnyKind.Postgres))
        assertTrue(kinds.contains(AnyKind.Sqlite))
    }

    @Test
    fun testAnyTypeInfo() {
        val info = AnyTypeInfo("INTEGER", AnyTypeInfoKind.Integer, isNull = false)
        assertEquals("INTEGER", info.name)
        assertEquals(AnyTypeInfoKind.Integer, info.kind)
        assertFalse(info.isNull)
    }

    @Test
    fun testAnyValueAndRef() {
        val value = AnyValue(42, AnyTypeInfo("INTEGER", AnyTypeInfoKind.Integer))
        assertEquals("INTEGER", value.typeInfo().name)
        assertFalse(value.isNull())
        assertEquals(42, value.asRaw())

        val nullVal = AnyValue(null, AnyTypeInfo("NULL", AnyTypeInfoKind.Null, isNull = true))
        assertTrue(nullVal.isNull())
        assertNull(nullVal.asRaw())

        val valRef = AnyValueRef("hello", AnyTypeInfo("TEXT", AnyTypeInfoKind.Text))
        assertEquals("TEXT", valRef.typeInfo().name)
        assertFalse(valRef.isNull())
        assertEquals("hello", valRef.asRaw())
    }

    @Test
    fun testAnyRowOperations() {
        val cols: List<Column<Database>> =
            listOf(
                DefaultColumn(0, "id", SimpleTypeInfo("INTEGER")),
                DefaultColumn(1, "title", SimpleTypeInfo("TEXT")),
            )
        val row = AnyRow(cols, listOf(10, "First Post"))

        assertEquals(2, row.columns().size)
        assertEquals(10, row.get(0))
        assertEquals("First Post", row.get(1))
        assertEquals(10, row.get("id"))
        assertEquals("First Post", row.get("title"))

        assertFailsWith<SqlxException.ColumnIndexOutOfBounds> {
            row.get(5)
        }
        assertFailsWith<SqlxException.ColumnNotFound> {
            row.get("nonexistent")
        }
    }

    @Test
    fun testAnyConnectOptions() {
        val opts = AnyConnectOptions("sqlite://:memory:")
        assertEquals("sqlite://:memory:", opts.databaseUrl)
    }

    @Test
    fun testAnyPoolProperties() {
        installDefaultDrivers()
        val pool = AnyPool("sqlite://:memory:")
        assertFalse(pool.isClosed())
        assertEquals(1, pool.size())
        assertEquals(1, pool.numIdle())
        assertNotNull(pool.options())
    }

    @Test
    fun testCustomDriverInstallation() {
        val customDriver =
            object : AnyDriver {
                override val name: String = "custom-test"

                override fun accepts(url: String): Boolean = url.startsWith("custom://")

                override suspend fun connect(url: String): Connection<AnyDatabase> =
                    AnyConnection(url)
            }

        installDrivers(listOf(customDriver))
        assertTrue(customDriver.accepts("custom://localhost/db"))
        assertFalse(customDriver.accepts("sqlite://test.db"))
    }
}
