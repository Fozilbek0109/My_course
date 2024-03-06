package uz.coder.d2kotlindatabase.database

import uz.coder.d2kotlindatabase.model.User

interface DbSrevice {
    fun addUser(user: User)
    fun deletUser(user: User)
    fun getAllUser():List<User>
    fun getUserCount():Int
    fun editUser()
    fun getUserById(id:Int)
}