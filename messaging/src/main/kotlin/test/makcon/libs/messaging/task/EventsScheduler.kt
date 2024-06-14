package test.makcon.libs.messaging.task

import net.javacrumbs.shedlock.core.DefaultLockingTaskExecutor
import net.javacrumbs.shedlock.core.LockConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import test.makcon.libs.messaging.config.MessagingSchedulingConfig
import test.makcon.libs.messaging.config.MessagingSchedulingConfig.MessagingLockProvider
import java.time.Duration
import java.time.Instant

@Component
@Import(MessagingSchedulingConfig::class)
@ConditionalOnProperty(value = ["test.makcon.events.send.enabled"], havingValue = "true", matchIfMissing = true)
class EventsScheduler(
    private val lockProvider: MessagingLockProvider,
    private val eventSender: EventSender,
) {

    @Scheduled(fixedDelayString = "\${test.makcon.events.send.period.millis:5000}")
    fun sendEvents() {
        DefaultLockingTaskExecutor(lockProvider)
            .executeWithLock(
                Runnable { eventSender.run() },
                LockConfiguration(
                    Instant.now(),
                    "events",
                    lockProvider.getLockAtMostFor(),
                    Duration.ZERO
                )
            )
    }
}