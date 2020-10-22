package com.bismuth.bismuth.objectDictionary

import com.bismuth.bismuth.objectDictionary.exceptions.ObjectDictionaryGuardianException
import com.bismuth.bismuth.project.ProjectGuardian
import com.bismuth.bismuth.user.User

class ObjectDictionaryGuardian {

    lateinit var user: User;

    companion object {

        fun onUser(user: User): ProjectGuardian {
            val guardian = ProjectGuardian();
            guardian.user = user;
            return guardian;
        }

        fun protectPublicAccessOf(objectDictionary: ObjectDictionary) {
            if (!objectDictionary.isPubliclyVisible)
                throw ObjectDictionaryGuardianException("The object dictionary you are trying to access is not publicly visible");
        }

    }


}

