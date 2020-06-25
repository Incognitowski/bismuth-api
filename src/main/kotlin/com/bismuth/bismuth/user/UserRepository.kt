package com.bismuth.bismuth.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM public.user WHERE username = :username", nativeQuery = true)
    fun findByUsername(@Param("username") username: String): User?

};