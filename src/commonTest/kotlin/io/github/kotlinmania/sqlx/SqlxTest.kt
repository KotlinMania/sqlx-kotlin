// port-lint: tests sqlx/src/lib.rs
package io.github.kotlinmania.sqlx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SqlxTest {
    @Test
    fun testEitherLeftAndRight() {
        val left: Either<Int, String> = Either.Left(42)
        assertTrue(left.isLeft)
        assertFalse(left.isRight)
        assertEquals(42, left.left())
        assertNull(left.right())

        val right: Either<Int, String> = Either.Right("hello")
        assertTrue(right.isRight)
        assertFalse(right.isLeft)
        assertEquals("hello", right.right())
        assertNull(right.left())

        val mappedLeft = left.mapLeft { it * 2 }
        assertEquals(84, (mappedLeft as Either.Left).value)

        val folded = right.fold({ "L:$it" }, { "R:$it" })
        assertEquals("R:hello", folded)
    }

    @Test
    fun testQueryAndArguments() {
        val q = query<Sqlite>("SELECT id, name FROM users WHERE id = ?")
        q.bind(1)
        assertEquals("SELECT id, name FROM users WHERE id = ?", q.sql())
        assertEquals(listOf<kotlin.Any?>(1), q.arguments()?.values())

        val qb = QueryBuilder<Sqlite>("SELECT * FROM users")
        qb.push(" WHERE age > ").pushBind(21)
        val built = qb.build()
        assertEquals("SELECT * FROM users WHERE age > ", built.sql())
        assertEquals(listOf<kotlin.Any?>(21), built.arguments()?.values())
    }

    @Test
    fun testRowAndColumns() {
        val cols: List<Column<AnyDatabase>> =
            listOf(
                DefaultColumn(0, "id", SimpleTypeInfo("INTEGER")),
                DefaultColumn(1, "name", SimpleTypeInfo("TEXT")),
            )
        val values: List<kotlin.Any?> = listOf(101, "Alice")
        val row = AnyRow(cols, values)
        assertEquals(2, row.columnCount())
        assertEquals(101, row.get(0))
        assertEquals("Alice", row.get(1))
        assertEquals(101, row.get("id"))
        assertEquals("Alice", row.get("name"))
        assertEquals(101, row.tryGet<Int>(0))
        assertEquals("Alice", row.tryGet<String>("name"))
    }

    @Test
    fun testAnyDriverAndPool() {
        installDefaultDrivers()
        val pool = AnyPool("sqlite://:memory:")
        assertFalse(pool.isClosed())
        assertEquals(1, pool.size())
        assertEquals(1, pool.numIdle())
    }
}
