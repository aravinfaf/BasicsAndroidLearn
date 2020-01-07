package com.e.learn.epoxy

import java.util.*
import kotlin.random.Random

class UserModel(var name:String,
                var email:String,
                var age:Int) {

    companion object{
        fun getSampleUsers():MutableList<UserModel>{
            val usersList:MutableList<UserModel> = mutableListOf()
            for (i in 1..50) {
                usersList.add(UserModel(name = "User $i",email = "user$i@email.com",age = Random.nextInt(18,50)))
            }
            return usersList
        }
    }
}
