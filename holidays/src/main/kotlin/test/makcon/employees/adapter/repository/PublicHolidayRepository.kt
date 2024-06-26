package test.makcon.employees.adapter.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import test.makcon.employees.adapter.doc.PublicHolidayDoc
import java.util.*

interface PublicHolidayRepository : MongoRepository<PublicHolidayDoc, UUID> {

    fun findByYearAndCountry(year: Int, country: String, page: Pageable): Page<PublicHolidayDoc>
}