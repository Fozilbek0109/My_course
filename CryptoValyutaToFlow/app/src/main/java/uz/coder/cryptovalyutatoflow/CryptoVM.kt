package uz.coder.cryptovalyutatoflow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import uz.coder.cryptovalyutatoflow.model.State

class CryptoVM:ViewModel() {

    private val repository = CryptoRepository
    var  i = 0
    val state:Flow<State> = repository.getCurrencyList()
        .map {
            Log.d(TAG, "map: ${i++}")
            State.Content(it) as State
        }
        .onStart {
            emit(State.Loading)
            Log.d(TAG, "onStart: ${i++}")
        }
        .onEach {
            Log.d(TAG, "onEach: ${i++}")
        }
        .onCompletion {
            Log.d(TAG, "onCompletion: ${i++}")
        }

    companion object{
        private const val TAG = "CryptoVM"
    }


}