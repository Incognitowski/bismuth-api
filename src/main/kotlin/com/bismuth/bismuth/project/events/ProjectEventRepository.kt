package com.bismuth.bismuth.project.events

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectEventRepository : JpaRepository<ProjectEvent, UUID>;