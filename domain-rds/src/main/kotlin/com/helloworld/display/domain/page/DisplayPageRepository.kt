package com.helloworld.display.domain.page

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface DisplayPageRepository : JpaRepository<DisplayPage, Long>, QuerydslPredicateExecutor<DisplayPage>
