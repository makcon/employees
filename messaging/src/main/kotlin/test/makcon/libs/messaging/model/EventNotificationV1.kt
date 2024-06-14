package test.makcon.libs.messaging.model

import java.time.Instant

data class EventNotificationV1(
    val status: Status = Status.PENDING,
    val attempts: Int = 0,
    val failedReason: String? = null,
    val executeAt: Instant? = Instant.now()
) {

    enum class Status {
        PENDING, SENT, FAILED
    }
}