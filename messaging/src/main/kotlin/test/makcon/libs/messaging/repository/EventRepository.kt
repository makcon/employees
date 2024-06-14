package test.makcon.libs.messaging.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import test.makcon.libs.messaging.repository.doc.EventDoc

interface EventRepository : MongoRepository<EventDoc, String> {

    @Query(
        value = "{'notification.status': 'PENDING', 'notification.executeAt': {'\$lte': new Date()}}",
        sort = "{'notification.executeAt': 1}"
    )
    fun findAllPending(page: Pageable): List<EventDoc>
}