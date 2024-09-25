import java.time.Clock
import java.util.*

class Logger(
    private val includeDateTime: Boolean,
    private val includeThreadName: Boolean,
    private val includeUniqueId: Boolean,
    private val clock: Clock
) {
    fun log(message: String) {
        if (includeDateTime) print("[${clock.instant()}] ")
        if (includeUniqueId) print("{${UUID.randomUUID()}} ")
        print(message)
        if (includeThreadName) print(" on ${Thread.currentThread().name} thread")
        println()
    }
}


fun main() {
    val logger = Logger(
        includeDateTime = true,
        includeThreadName = true,
        includeUniqueId = false,
        clock = Clock.systemDefaultZone()
    )
    logger.log("Application initialized")
}