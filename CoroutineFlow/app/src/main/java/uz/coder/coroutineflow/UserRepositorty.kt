package uz.coder.coroutineflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserRepositorty {
    val listUsers = mutableListOf("Mustafo","LAzizbek","Abdulaziz")

    suspend fun addUser(string: String){
        delay(500)
        listUsers.add(string)
    }

    fun loadUser():Flow<List<String>> = flow {
        while (true){
            delay(500)
            emit(listUsers)
        }
    }

}