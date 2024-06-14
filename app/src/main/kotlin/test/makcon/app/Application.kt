package test.makcon.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["test.makcon"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
