import java.time.Clock
import java.util.*

interface Logger {
    fun log(message: String)
}

class ConsoleLogger : Logger {
    override fun log(message: String) {
        print(message)
    }
}

class UniqueIdLogger(private val logger: Logger) : Logger {
    override fun log(message: String) =
        logger.log("{${UUID.randomUUID()}} $message")
}

class ThreadNameLogger(private val logger: Logger) : Logger {
    override fun log(message: String) =
        logger.log("$message (on ${Thread.currentThread().name} thread)")
}

class DateTimeLogger(private val logger: Logger, private val clock: Clock = Clock.systemDefaultZone()) : Logger {
    override fun log(message: String) =
        logger.log("[${clock.instant()}] $message")
}

fun main() {
    val logger = UniqueIdLogger(ThreadNameLogger(DateTimeLogger(ConsoleLogger())))
    logger.log("Application initialized")
}