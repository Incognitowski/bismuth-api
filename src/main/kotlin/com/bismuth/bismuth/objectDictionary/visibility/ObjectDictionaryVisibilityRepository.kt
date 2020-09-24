package com.bismuth.bismuth.objectDictionary.visibility

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ObjectDictionaryVisibilityRepository : JpaRepository<ObjectDictionaryVisibility, UUID>;