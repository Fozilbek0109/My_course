package uz.coder.d2testservice.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {
    private val scope = CoroutineScope(Dispatchers.Main)
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        log("onCreate")
        super.onCreate()
    }

    private fun log(src: String) {
        Log.d("MyService", "Timer: $src")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       /**
        * biz bu holatda ham ishlatishimiz mumkun edi lekin bu bizning main potokni blolaydi
        for (i in 0..10) {
            Thread.sleep(1000)
            log(i)
        }
        *shu sabab biz caroutins dan foydalanamiz
        **/
        log("onStartCommand")
        scope.launch {
            for (i in 0..10) {
                /**
                 * delay ushbu qatorda codni 1 sekund kutadi va yana ishni davom ettradi bunda
                 * 1 sekun kutish vaqtida u scope ichidan chiqib ketadi
                 */
                delay(1000)
                log(i.toString())
            }
        }
        /**
         * returinda qaytarishimiz mumkin bulgan Statik keyalr:
         * START_STIKY dastur onDestroy bolganda service  onDestroy bolmaydi va  ishlashda davom etadi
         * START_NOT_STIKY dastur onDestroy bolganda service ham onDestroy boladi va  ishlashdan to'xtaydi
         * START_REDELIVER_INTENT bu holatda bining dastur intent orqali ishlata olamiz...
         * default holatdagi super.onStartCommand(intent, flags, startId) metodi START_STYKY vazifasini bajaradi
         */
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MyService::class.java)
        }
    }

}