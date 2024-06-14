package test.makcon.libs.messaging.config

import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import net.javacrumbs.shedlock.support.KeepAliveLockProvider
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import java.io.Closeable
import java.time.Duration
import java.util.concurrent.Executors

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
class MessagingSchedulingConfig {

    @Bean
    fun messagingLockProvider(mongoTemplate: MongoTemplate) = MessagingLockProvider(mongoTemplate)

    class MessagingLockProvider(mongoTemplate: MongoTemplate) :
        KeepAliveLockProvider(MongoLockProvider(mongoTemplate.db), executorService),
        Closeable {

        override fun close() {
            executorService.shutdown()
        }

        fun getLockAtMostFor(): Duration = Duration.ofSeconds(30)

        companion object {

            private val executorService = Executors.newSingleThreadScheduledExecutor()
        }
    }
}