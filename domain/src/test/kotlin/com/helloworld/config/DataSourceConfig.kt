package com.helloworld.config

import com.helloworld.RdsProjectListener
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import javax.sql.DataSource


@TestConfiguration
class DataSourceConfig {
    @Bean
    fun dataSource(): DataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = RdsProjectListener.rdsContainer.jdbcUrl
        hikariConfig.username = RdsProjectListener.rdsContainer.username
        hikariConfig.password = RdsProjectListener.rdsContainer.password
        return HikariDataSource(hikariConfig)
    }
}