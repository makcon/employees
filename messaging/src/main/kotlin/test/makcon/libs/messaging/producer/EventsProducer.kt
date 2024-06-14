package test.makcon.libs.messaging.producer

import test.makcon.libs.messaging.model.EventNotificationV1
import test.makcon.libs.messaging.repository.doc.EventDoc

interface EventsProducer {

    fun send(entity: EventDoc): EventNotificationV1
}