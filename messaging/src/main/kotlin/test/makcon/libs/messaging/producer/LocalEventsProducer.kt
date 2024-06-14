package test.makcon.libs.messaging.producer

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import test.makcon.libs.messaging.model.EventNotificationV1
import test.makcon.libs.messaging.model.EventNotificationV1.Status
import test.makcon.libs.messaging.repository.doc.EventDoc

@Component
class LocalEventsProducer(
    private val eventPublisher: ApplicationEventPublisher,
    @Value("\${test.makcon.events.max-retries:50}")
    private val maxRetries: Int,
) : EventsProducer {

    private val log = KotlinLogging.logger {}

    override fun send(entity: EventDoc): EventNotificationV1 = try {
        log.info("Sending event: {} to topic: {}", entity.event, entity.topic)
        eventPublisher.publishEvent(entity.event)

        EventNotificationV1(
            status = Status.SENT,
            attempts = entity.notification.attempts + 1,
            executeAt = null
        )
    } catch (e: Exception) {
        log.error("Failed to send event: ${entity.event.id}", e)
        buildFailedResult(entity.notification, e)
    }

    private fun buildFailedResult(notification: EventNotificationV1, e: Exception): EventNotificationV1 {
        val limitExceeded = notification.attempts >= maxRetries
        val attempts = notification.attempts + 1
        val nextExecuteAt = if (limitExceeded) null else
            notification.executeAt?.plusSeconds(10 * attempts.toLong())

        return EventNotificationV1(
            status = if (limitExceeded) Status.FAILED else Status.PENDING,
            attempts = attempts,
            failedReason = e.message,
            executeAt = nextExecuteAt
        )
    }
}
