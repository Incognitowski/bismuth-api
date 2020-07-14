package com.bismuth.bismuth.application.events

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApplicationEventRepository : JpaRepository<ApplicationEvent, UUID>;