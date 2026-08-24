import Testing
import Sqlx

@Suite("Sqlx Swift Export Tests")
struct SqlxExportTests {
    @Test("Swift module loads cleanly")
    func testSwiftModuleLoads() {
        #expect(Bool(true))
    }

    @Test("Exported types are accessible")
    func testExportedTypes() {
        let pool = AnyPool(url: "sqlite://:memory:", poolOpts: PoolOptions())
        #expect(!pool.isClosed())
        #expect(pool.size() == 1)
        #expect(pool.numIdle() == 1)
    }
}
