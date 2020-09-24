package com.bismuth.bismuth.httpAPI.visibility

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HttpAPIVisibilityRepository : JpaRepository<HttpAPIVisibility, UUID>