package com.bismuth.bismuth.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM public.user WHERE username = :username", nativeQuery = true)
    fun findByUsername(@Param(value = "username") username: String): User?;

    @Query(value = "SELECT * FROM public.user WHERE UPPER(username) LIKE UPPER(:username)", nativeQuery = true)
    fun searchByUsername(@Param(value = "username") username: String): List<User>;

    @Query(value = "SELECT * FROM public.user WHERE username = :username AND password = :password", nativeQuery = true)
    fun findByUsernameAndPassword(
            @Param(value = "username") username: String,
            @Param(value = "password") password: String
    ): User?;

    @Query(value = "SELECT * FROM public.user WHERE email = :email", nativeQuery = true)
    fun findByEmail(@Param(value = "email") email: String): User?;

    @Query(value = "SELECT * FROM public.user WHERE softdeleted = false", nativeQuery = true)
    override fun findAll(): MutableList<User>;

    @Query(value = "SELECT * FROM public.user WHERE user_id IN (SELECT pv.user_id FROM project_visibility pv WHERE project_id = :projectId AND pv.softdeleted = false) AND softdeleted = false", nativeQuery = true)
    fun getUsersRelatedToProject(@Param("projectId") projectId: UUID): List<User>;

};