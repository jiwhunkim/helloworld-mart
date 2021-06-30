package com.helloworld.rds.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = ["com.helloworld.domain"])
@EnableTransactionManagement
class RdsConfig
