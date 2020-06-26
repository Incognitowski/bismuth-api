package com.bismuth.bismuth.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService;

    @PostMapping("")
    fun createUser(@RequestBody user: User): User = userService.createUser(user);

    @GetMapping("")
    fun getUsers(): List<User> = userService.getUsers();

    @GetMapping("by-username/{username}")
    fun findByUsername(@PathVariable(value = "username") username : String): User? = userService.getByUsername(username);

}