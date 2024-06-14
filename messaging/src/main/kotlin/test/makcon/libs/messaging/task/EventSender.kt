package test.makcon.libs.messaging.task

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import test.makcon.libs.messaging.producer.EventsProducer
import test.makcon.libs.messaging.repository.EventRepository
import test.makcon.libs.messaging.repository.doc.EventDoc

private val log = KotlinLogging.logger {}

@Component
class EventSender(
    private val eventRepository: EventRepository,
    private val eventsProducer: EventsProducer,
    @Value("\${test.makcon.events.page-size:20}")
    private val pageSize: Int
) {

    fun run() {
        var events: List<EventDoc>
        do {
            events = eventRepository.findAllPending(PageRequest.ofSize(pageSize))
            events.forEach { sendOne(it) }
        } while (events.isNotEmpty())
    }

    private fun sendOne(eventDoc: EventDoc) {
        val notification = eventsProducer.send(eventDoc)

        try {
            eventRepository.save(eventDoc.copy(notification = notification))
        } catch (e: Exception) {
            log.error("Failed to update event doc: ${eventDoc.id}", e)
        }
    }
}