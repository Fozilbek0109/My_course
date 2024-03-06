package uz.coder.cryptovalyutatoflow.model

sealed class State {
    data object Initial : State()
    data object Loading : State()
    data class Content(val curensyList: List<Coin>) : State()
}
