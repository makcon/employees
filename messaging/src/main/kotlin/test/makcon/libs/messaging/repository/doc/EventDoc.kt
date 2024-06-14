package test.makcon.libs.messaging.repository.doc

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import test.makcon.libs.messaging.model.EventNotificationV1
import test.makcon.libs.messaging.model.EventV1
import java.util.*

@Document("events")
@CompoundIndexes(
    CompoundIndex(def = "{'notification.status': 1, 'notification.executeAt': 1}"),
)
data class EventDoc(
    val id: UUID = UUID.randomUUID(),
    @field:Indexed
    val entityId: UUID,
    val topic: String,
    val notification: EventNotificationV1 = EventNotificationV1(),
    val event: EventV1,
)