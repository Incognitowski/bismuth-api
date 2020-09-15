package com.bismuth.bismuth.user

import java.util.*

class UserCommons {

    companion object {

        fun parseMap(users : List<User>): Map<UUID, User> {
            val userMap = mutableMapOf<UUID, User>();
            users.forEach {
                userMap[it.userId!!] = it;
            }
            return userMap;
        }

    }

}