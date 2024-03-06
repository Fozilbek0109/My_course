package uz.coder.coroutineflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = UserRepositorty
    private val _user = MutableLiveData<List<String>>()
    val user: LiveData<List<String>>
        get() = _user
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        repository.loadUser()
    }


    fun addUser(str: String) {
        scope.launch {
            repository.addUser(str)
        }
        loadUsers()
    }

    fun loadUsers() {
        scope.launch {
            repository.loadUser().collect {
                _user.value = it
            }
        }
    }
}