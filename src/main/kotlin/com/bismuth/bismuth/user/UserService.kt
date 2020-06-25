package com.bismuth.bismuth.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository;

    fun createUser(user: User): User {
        user.user_id = UUID.randomUUID();
        return userRepository.save(user);
    }

    fun getByUsername(username: String): User? = userRepository.findByUsername(username);

    fun getUsers(): List<User> = userRepository.findAll();

}