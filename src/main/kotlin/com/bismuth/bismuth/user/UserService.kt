package com.bismuth.bismuth.user

import com.bismuth.bismuth.framework.exception.EntityNotFoundException
import com.bismuth.bismuth.framework.exception.UniqueConstraintException
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
        if (userRepository.findByUsername(user.username as String) != null)
            throw UniqueConstraintException("Sorry, but that username is not available.");
        if (userRepository.findByEmail(user.email) != null)
            throw UniqueConstraintException("The email you entered is already being used in another account.");

        return userRepository.save(user);
    }

    fun getById(id: UUID): User {
        val user: Optional<User> = userRepository.findById(id);
        if (!user.isPresent)
            throw EntityNotFoundException("We were unable to find a user with such UUID.")
        return user.get();
    };

    fun getByUsername(username: String): User? = userRepository.findByUsername(username);

    fun getUsers(): List<User> = userRepository.findAll();

}