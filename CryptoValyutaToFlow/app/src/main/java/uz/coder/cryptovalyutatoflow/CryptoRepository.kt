package uz.coder.cryptovalyutatoflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.coder.cryptovalyutatoflow.model.Coin
import kotlin.random.Random

object CryptoRepository {
    private val currencyName = listOf("BTC", "ETH", "USDT", "BNB", "USDC")
    private val currencyList = mutableListOf<Coin>()

    fun getCurrencyList(): Flow<List<Coin>> = flow {
        while (true) {
            delay(3000)
            generateCurensyList()
            emit(currencyList)
        }


    }


    private fun generateCurensyList() {
        val prices = buildList {
            repeat(currencyName.size) {
                add(Random.nextInt(1000, 2000))
            }
        }
        val newData = buildList {
            for ((index, currencyName) in currencyName.withIndex()) {
                val prise = prices[index]
                val currency = Coin(name = currencyName, prise = prise)
                add(currency)
            }
        }
        currencyList.clear()
        currencyList.addAll(newData)
    }


}