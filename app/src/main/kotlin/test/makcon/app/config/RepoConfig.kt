package test.makcon.app.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories("test.makcon.**.repository")
class RepoConfig {

    @Bean
    @ConditionalOnProperty(
        value = ["test.makcon.transactions.enabled"],
        havingValue = "true",
        matchIfMissing = true
    )
    fun transactionManager(dbFactory: MongoDatabaseFactory) = MongoTransactionManager(dbFactory)
}