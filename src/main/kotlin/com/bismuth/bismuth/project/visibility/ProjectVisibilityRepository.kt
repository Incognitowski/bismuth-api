package com.bismuth.bismuth.project.visibility

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectVisibilityRepository : JpaRepository<ProjectVisibility, UUID>;