package uz.coder.cryptovalyutatoflow.lesson5

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

val scope = CoroutineScope(Dispatchers.IO)

/*suspend fun main() {
    /**
     * cold flow qoidalar
     * 1. cold flow qachonki unga terminal aperatori orqali ulansak ishini davom ettiradi yoqsa ishlamaydi
     * 2. cold flowga ulangan terminal aperatoriga uning malumoti kerak bo'lmasa u ishlamaydi
     * 3. cold flofga ulangan har bir terminal aperatori uchun alohida patok danniy ishlatadi
     */
    val flow = getFlow()    //cold flow

    val job = scope.launch {
        flow.collect {
            println("collect 1 -$it")
        }
    }
    delay(5000)
    val job2 = scope.launch {
        flow.collect {
            println("collect 2 -$it")
        }
    }

    job.join()
    job2.join()
}

fun getFlow(): Flow<Int> = flow {
    repeat(100) {
        println("Emmit $it")
        emit(it)
        delay(1000)
    }
}*/
suspend fun main() {
    /**
     * Hot flow qoidalari ~  cold flowning teskarisi
     * 1. Hot flow nechta terminal aperatori ulansa ham bitta patok danniy ishlatadi
     * 2. Hot flowga terminal aperatori ulanmasa ham u ishini davom ettiraveradi
     * 3. Hot flowning malumoti terminal aperatoriga kerak bo'lmasaham ishini davom ettiradi va uni to'xtatib bo'lmaydi.
     */

    val flow = MutableSharedFlow<Int>()  // hot flow
    scope.launch {
        repeat(100) {
            println("Emmit $it")
            flow.emit(it)
            delay(1000)
        }
    }

    val job = scope.launch {
        flow.collect {
            println("collect 1 -$it")
        }
    }
    delay(5000)
    val job2 = scope.launch {
        flow.collect {
            println("collect 2 -$it")
        }
    }
    // biz flowda ikkita terminal aperatoridan foydalandik natijada dastur ishlamadi sababi
    // asosiy patok malumotni chiqarishga ulgurmadi buning oldini olish uchun biz joblarni asosiy
    // patokka qo'shish(join) qilishimiz lozim
    // natijada asosiy patok job larning ishini tugatishini kutadi
    job.join()
    job2.join()
}


