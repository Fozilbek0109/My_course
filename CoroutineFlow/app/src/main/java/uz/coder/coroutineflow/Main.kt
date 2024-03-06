package uz.coder.coroutineflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


suspend fun main() {
    val list = listOf(1, 15, 16, 144, 13, 3, 8, 46, 77, 15, 63).asFlow()
        getFlowByFlowBuilder().filter {
            it > 20
        }.filter {
            it.isPrime()
        }.collect {
            println(it)
        }
}

fun getFlowByFlowofBuelder(): Flow<Int> {
    return flowOf(1, 15, 16, 144, 13, 3, 8, 46, 77, 15, 63)
}

fun getFlowByFlowBuilder(): Flow<Int> {
    val list = listOf(1, 15, 16, 144, 13, 3, 8, 46, 77, 15, 63)
    return flow {
        list.forEach {
            emit(it)
        }
    }
}


suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this) {
        delay(10)
        if (this % 2 == 0) return false
    }
    return true
}