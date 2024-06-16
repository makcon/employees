package test.makcon.employees.adapter.repository

import org.springframework.data.mongodb.repository.MongoRepository
import test.makcon.employees.adapter.doc.EmployeeDoc
import java.util.*

interface EmployeeRepository : MongoRepository<EmployeeDoc, UUID>