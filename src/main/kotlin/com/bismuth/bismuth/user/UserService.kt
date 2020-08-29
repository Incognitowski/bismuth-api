package com.bismuth.bismuth.user

import com.auth0.jwt.interfaces.DecodedJWT
import com.bismuth.bismuth.framework.exception.EntityNotFoundException
import com.bismuth.bismuth.framework.exception.UniqueConstraintException
import com.bismuth.bismuth.project.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository;

    fun createUser(user: User): User {
        user.userId = UUID.randomUUID();
        UserBO.validate(user);
        user.password = BCryptPasswordEncoder().encode(user.password);
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

    fun getByDecodedJWT(decodedJWT: DecodedJWT): User {
        return getByUsername(decodedJWT.subject);
    }

    fun getByUsername(username: String): User {
        val user = userRepository.findByUsername(username)
                ?: throw EntityNotFoundException("We were unable to find a user with such username.");
        return user;
    }

    fun getUsers(): List<User> = userRepository.findAll();

    fun getUsersRelatedToProject(project: Project): List<User> {
        return userRepository.getUsersRelatedToProject(project.projectId!!);
    }

    fun searchUsersByUsername(username: String): List<User> {
        return userRepository.searchByUsername(username);
    }

}