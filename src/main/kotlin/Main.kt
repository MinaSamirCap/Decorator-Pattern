import java.time.Clock
import java.util.*
fun interface Logger {
    fun log(message: String)
}

val consoleLogger = Logger { message -> print(message) }
fun Logger.withUniqueId() = Logger { log("{${UUID.randomUUID()}} $it") }
fun Logger.withThreadName() = Logger { log("$it (on ${Thread.currentThread().name} thread)") }
fun Logger.withDateTime(clock: Clock = Clock.systemDefaultZone()) = Logger { log("[${clock.instant()}] $it") }

fun main() {
    val logger = consoleLogger.withUniqueId().withThreadName().withDateTime()
    logger.log("Application initialized")
}


/// references ...
//https://www.youtube.com/watch?v=erWsXSqQ-CM